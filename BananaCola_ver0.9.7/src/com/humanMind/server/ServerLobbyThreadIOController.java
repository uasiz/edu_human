package com.humanMind.server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import com.humanMind.client.InGameMemberId;

public class ServerLobbyThreadIOController {

	private Socket sock;
	private DatagramSocket dsock;
	private OutputStream sendMsg; // 메세지를 보낼 때 사용
	private InputStream reMsg; // 메세지를 받을 때 사용
	private BufferedOutputStream bos;
	private BufferedReader br;

	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	private InetAddress ia;

	private String id;
	private String msg;
	private int roomNum = 0;

	private Object inObject = null;

	private InGameMemberId inMem = null;
	private ServerStartJFrame serverFrame;
	private ServerInUser sInUser;
	private ServerThreadIOController stioc;

	private HashMap<Integer, String> roomMap;
	private Vector<String> inRoomId = new Vector<>();
	private ArrayList<InetAddress> inIp;
	private ArrayList<InGameMemberId> inUserList;

	ServerLobbyThreadIOController(ServerThreadIOController s, InGameMemberId inMem, Socket c, DatagramSocket d) {
		this.stioc = s;
		this.sock = c;
		this.inMem = inMem;
		this.id = inMem.getId();
		this.roomNum = inMem.getLocation();
		this.dsock = d;
		ia = sock.getInetAddress();
		serverFrame = ServerStartJFrame.getInstance();
		serverFrame.setInUserMap(id, this);
		sInUser = ServerInUser.getInstance();
		sInUser.setInUser(id, this);
		streamSet();
		receiveData();
	}

	// 처음 로그인 시 클라이언트에게 보내는 메세지
	private void streamSet() {
		try {
			bos = new BufferedOutputStream(sock.getOutputStream());
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String greetingMsg = "[ 관리자 ] : 0" + id + "님 안녕하세요!";
		setMsg(greetingMsg);
		sendData();
	}

	public void sendData() {
		try {
			bos = new BufferedOutputStream(sock.getOutputStream());
			bos.write(msg.getBytes());
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receiveData() {
		Runnable task = () -> {

			try {
				while (true) {

					br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					String data = br.readLine();

					// 방 번호를 받아 같은 방 번호인 사람들에게만 전송
					roomNum = Character.getNumericValue(data.charAt(0));
					String logdata = data.substring(1);
					String protocol = "";
					if (logdata.length() > 11) {
						protocol = logdata.substring(0, 11);
					}

					if (protocol.equals("CreateRoom/")) {
						String roomName = logdata.substring(11);
						serverFrame.inLog(id + " 님이  '" + roomName + "' 방을 만들었습니다." + "\n");
						roomNum = serverFrame.makeRoom(roomName);
						roomMap = serverFrame.getRoomMap();

						serverFrame.sendTo(id, "EnterRoom/" + String.valueOf(roomNum));

						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						serverFrame.sendAll(" ", "CreateRoom/" + roomNum + "/" + roomName);

						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						inRoomId.add(id);
						serverFrame.sendAll(" ", "InList/" + id);

					} else if (protocol.equals("EnterRoomm/")) {
						String tmpRoomId = logdata.substring(13);
						inRoomId.add(tmpRoomId);
						for (int i = 0; i < inRoomId.size(); i++) {
							serverFrame.sendAll(" ", "InList/" + inRoomId.get(i));
						}
					} else if (protocol.equals("LeaveRoomm/")) {
						String tmpRoomId = logdata.substring(11);

						inRoomId.remove(tmpRoomId);
						serverFrame.sendAll(" ", "LeaveId/" + tmpRoomId);

					} else if (protocol.equals("EnterPaint/")) {
						// String protocol = "EnterPaint/x/"+x+"/y/"+y;
						String tmpXY = logdata.substring(11);
						String x = "";
						String y = "";
						String size = "";

						StringTokenizer st1 = new StringTokenizer(tmpXY, "/");
						while (st1.hasMoreTokens()) {
							try {
								x = st1.nextToken();
								y = st1.nextToken();
								size = st1.nextToken();
							} catch (Exception e) {
							}

						}
						serverFrame.sendAll(id, "EnterXY/" + x + "/" + y + "/" + size);

					} else if (protocol.equals("ClearPaint/")) {
						serverFrame.sendAll(id, "ClearCan");
					} else if (protocol.equals("ColorPaint/")) {
						serverFrame.sendAll(id, logdata);
					} else if (protocol.equals("SendAnswer/")) {
						String tmpAnswer = logdata.substring(11);
						String hostId = "";
						String answerId = "";
						String answer = "";
						StringTokenizer st1 = new StringTokenizer(tmpAnswer, "/");
						while (st1.hasMoreTokens()) {
							hostId = st1.nextToken();
							answerId = st1.nextToken();
							answer = st1.nextToken();
						}
						serverFrame.sendTo(hostId, "Answer/" + answerId + "/" + answer);
					} else if (protocol.equals("RetunWrong/")) {
						String answerId = logdata.substring(11);
						serverFrame.sendTo(answerId, "Popup/" + answerId + "/틀렸습니다!");
					} else if (protocol.equals("RetunRight/")) {
						String answerId = logdata.substring(11);
						serverFrame.sendAll(" ", "Popup/" + answerId + "/정답입니다!");
					} else if (protocol.equals("SenUDPPort/")) {
						String inUDP = logdata.substring(11);
						String inId = ""; 
						String inIp = "";
						String inPort = "";
						StringTokenizer st1 = new StringTokenizer(inUDP, "/");
						while (st1.hasMoreTokens()) {
							inId = st1.nextToken();
							inIp = st1.nextToken();
							inPort = st1.nextToken();
						}
						String log = id+"님이 " + inIp + "에서 접속하였습니다." + "\n";
						serverFrame.inLog(log);
						serverFrame.setInUserList(inId, inIp, Integer.parseInt(inPort));
						getPaintXY();
					} else {

						int start = -1;

						start = logdata.indexOf("/");

						if (start == 0) {
							try {
								int end = logdata.indexOf(" ", 4);
								String to = logdata.substring(start + 4, end);
								String sendMsg = "[ " + id + "로부터의 귓말 ] : 9" + logdata.substring(end + 1);
								serverFrame.sendTo(to, sendMsg);
								serverFrame.inLog(id + " -> " + to + " : " + logdata.substring(end + 1) + "\n");
							} catch (Exception e) {

							}

						} else if (start != 0) {
							serverFrame.inChat(id, data + "\n");
							serverFrame.sendAll(id, "[ " + id + " ] : " + data);
						}

					}

				}
			} catch (IOException e) {
				// 로그아웃
				sInUser.setOutUser(id);

				try {
					bos.close();
					br.close();
					sock.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		};

		Thread t = new Thread(task);
		t.start();
	}

	public void setMsg(String msg) {
		this.msg = this.roomNum + msg + "\n";
	}

	public InetAddress getIp() {
		return this.ia;
	}

	public void getPaintXY() {

		Runnable task = () -> {

			while (true) {
				inUserList = serverFrame.getUserList();

				String line = null;

				try {

					byte[] buffer = new byte[1024];
					DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
					dsock.receive(receivePacket);
					String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());

					for(int i = 0; i < inUserList.size(); i++) {
						DatagramPacket sendPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, 
								inUserList.get(i).getIa(), inUserList.get(i).getPort());
						dsock.send(sendPacket);
					}
					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};

		Thread t = new Thread(task);
		t.start();

	}

}
