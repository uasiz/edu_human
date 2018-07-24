package com.humanMind.client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

public class ClientChatThread {

	private Socket sock;
	private Socket myInfoSock;
	private Socket inGameListSock;
	private DatagramSocket dsock;
	
	private InetAddress ia; 
	private String myIp;

	private OutputStream sendMsg; // 메세지를 보낼 때 사용
	private InputStream reMsg; // 메세지를 받을 때 사용
	private BufferedOutputStream bos;
	private BufferedReader br;

	private ObjectInputStream ois;

	private Object inObject;

	private String id;
	private String msg;
	private int roomNum = 0;
	private int port = 0;
	
	private Set set;
	private Iterator iterator;

	private Map<String, MemberSerial> myMap;
	private ArrayList<String> inUserList;
	private HashMap<Integer, String> roomMap = new HashMap<>();
	private Vector<String> inRoomId = new Vector<>();

	private ClientLobbyJFrame clientFrame;
	private InGameMemberId inGameMember;

	private int x, y, size;
	private String color;

	ClientChatThread(Socket c, Socket m, Map<String, MemberSerial> myMap, Socket i, DatagramSocket d) {
		this.sock = c;
		this.myInfoSock = m;
		this.inGameListSock = i;
		this.myMap = myMap;
		this.dsock = d;
		this.port = dsock.getLocalPort();
		
		try {
			ia = InetAddress.getByName("192.168.55.64");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		set = myMap.keySet();
		iterator = set.iterator();
		while (iterator.hasNext()) {
			this.id = (String) iterator.next();
		}
		sendPort();
		getInGameList();
		clientFrame = new ClientLobbyJFrame(sock, myInfoSock, this, id, myMap);
		streamSet();
		receiveData();
	}
	
	public void sendPort() {
		try {
			myIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMsg("SenUDPPort/"+id+"/" +myIp+"/"+ port);
		sendData();
	}

	public void sendPaintXY(String s) {
		
		try {
			String line = s;
			DatagramPacket sendPacket = new DatagramPacket(line.getBytes(), 
					line.getBytes().length, ia, 6670);
			dsock.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receivePaintXY() {
		Runnable task = () -> {
			try {
				while (true) {
					
					
					byte[] buffer = new byte[1024];
					DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
					dsock.receive(receivePacket);
					String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());

					StringTokenizer st1 = new StringTokenizer(msg, "/");
					while (st1.hasMoreTokens()) {
						try {
							x = Integer.parseInt(st1.nextToken());
							y = Integer.parseInt(st1.nextToken());
							size = Integer.parseInt(st1.nextToken());

							clientFrame.setPaint(x, y, size);
						} catch (Exception e) {
						}
					}
					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};

		Thread t = new Thread(task);
		t.start();
	}

	public void streamSet() {

		try {
			bos = new BufferedOutputStream(sock.getOutputStream());
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMsg(id + "님이 입장하였습니다.");
		sendData();
	}

	public void sendData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					bos = new BufferedOutputStream(sock.getOutputStream());

					bos.write(msg.getBytes());
					bos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void receiveData() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					while (true) {
						br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
						String data = br.readLine();

						String tmpMsg = data.substring(1);

						int i1 = -1;
						i1 = data.indexOf(":") + 2;

						if (i1 != 1) {
							try {
								String sendId = tmpMsg.substring(0, i1 - 2);
								int receiveRoomNum = data.charAt(i1) - 48;
								String txt = tmpMsg.substring(i1);
								String reMsg = sendId + " " + txt;

								if (receiveRoomNum == 0) {
									clientFrame.setMsg(reMsg);
								} else if (receiveRoomNum == roomNum) {
									clientFrame.setRoomMsg(reMsg);
								} else if (receiveRoomNum == 9) {
									if (roomNum == 0) {
										clientFrame.setMsg(reMsg);
									} else {
										clientFrame.setRoomMsg(reMsg);
									}

								}
							} catch (Exception e) {

							}

						} else if (i1 == 1) {
							String protocol = "";
							int tmpRoomNum = -1;
							String tmpRoomName = "";
							StringTokenizer st1 = new StringTokenizer(tmpMsg, "/");
							while (st1.hasMoreTokens()) {
								protocol = st1.nextToken();
								if (protocol.equals("EnterRoom")) {
									roomNum = Integer.parseInt(st1.nextToken());
								} else if (protocol.equals("CreateRoom")) {
									tmpRoomNum = Integer.parseInt(st1.nextToken());
									tmpRoomName = st1.nextToken();
									roomMap.put(tmpRoomNum, tmpRoomName);
								} else if (protocol.equals("InList")) {
									String myId = st1.nextToken();
									inRoomId.add(myId);
								} else if (protocol.equals("LeaveId")) {
									String leaveId = st1.nextToken();
									inRoomId.remove(leaveId);
								} else if (protocol.equals("ClearCan")) {
									clientFrame.clearCan();
								} else if (protocol.equals("ColorPaint")) {
									clientFrame.setColor(st1.nextToken());
								} else if (protocol.equals("Answer")) {
									// "Answer/" +answerId + "/" + answer
									Thread.sleep(100);
									String answerId = st1.nextToken();
									String answer = st1.nextToken();
									Boolean answerCheck = clientFrame.answerCheck(answer);
									if (answerCheck) {
										// 정답 메시지 보내기 + 호스트 문제 바꾸기
										setMsg("RetunRight/" + answerId);
										sendData();
									} else {
										// 오답 메시지 보내기
										setMsg("RetunWrong/" + answerId);
										sendData();
									}
								} else if (protocol.equals("Popup")) {
									String answerId = st1.nextToken();
									String isRight = st1.nextToken();
									if (isRight.equals("정답입니다!")) {
										clientFrame.setPopup("정답입니다!", answerId + "님이 맞추셨습니다!");

										clientFrame.setQuiz();

									} else if (isRight.equals("틀렸습니다!")) {
										clientFrame.setPopup("틀렸습니다!", "정답이 아닙니다!");
									}
								}
							}
							clientFrame.inRoomIdWrite();

						}

					}
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}

			}

		}).start();
	}

	public String getId() {
		return this.id;
	}

	public void setMsg(String msg) {

		this.msg = this.roomNum + msg + "\n";

	}

	public void getInGameList() {
		Runnable task = () -> {

			while (true) {
				try {

					ois = new ObjectInputStream(inGameListSock.getInputStream());
					Object tempObj;
					tempObj = ois.readObject();
					if (tempObj instanceof ArrayList) {
						inUserList = (ArrayList) tempObj;
					}

				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};

		Thread t = new Thread(task);
		t.start();
	}

	public ArrayList<String> getInGameId() {
		return this.inUserList;
	}

	public HashMap<Integer, String> getRoomMap() {
		return this.roomMap;
	}

	public void setRoomNum(int num) {
		this.roomNum = num;
	}

	public Vector<String> getInRoomIdList() {
		return this.inRoomId;
	}

}
