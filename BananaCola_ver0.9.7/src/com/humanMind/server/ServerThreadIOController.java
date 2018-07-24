package com.humanMind.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.humanMind.client.IdList;
import com.humanMind.client.InGameMemberId;
import com.humanMind.client.MemberSerial;
import com.humanMind.client.MyLogin;
import com.humanMind.client.RequestMyInfo;
import com.humanMind.database.MemberDao;
import com.humanMind.database.MemberDto;

public class ServerThreadIOController {

	private Socket joinSock;
	private Socket loginSock;
	private Socket chatSock;
	private Socket myInfoSock;
	private Socket inGameListClient;

	private DatagramSocket dsock;
	
	private ServerStartJFrame ssj;

	private Object inObject = null;
	
	private RequestMyInfo myIdInfo = new RequestMyInfo();
	private MyLogin myLogin = new MyLogin();
	private IdList myId = new IdList();
	private MemberSerial member = new MemberSerial();

	private Map<String, MemberDto> memberMap;
	private Map<String, MemberDto> sendMemberInfo;
	private Map<String, InGameMemberId> inGameMemberMap;
	
	ServerThreadIOController(Socket j, Socket l, Socket c, Socket m, Socket i, DatagramSocket d) {
		this.joinSock = j;
		this.loginSock = l;
		this.chatSock = c;
		this.myInfoSock = m;
		this.inGameListClient = i;
		this.dsock = d;

		ssj = ServerStartJFrame.getInstance();
		receiveObjectSet();
	}

	public void receiveObjectSet() {

		receiveJoinObj();
		receiveLoginObj();
		receiveMyInfoObj();
		sendInGameMemberMap();
		// receiveChatObj();

	}

	public void receiveJoinObj() {

		Runnable task = () -> {

			while (true) {
				try {
					ObjectInputStream ois = new ObjectInputStream(joinSock.getInputStream());
					inObject = ois.readObject();
					if (inObject instanceof IdList) {
						receiveIdDupleCheck(inObject);
					} else if (inObject instanceof MemberSerial) {
						insertNewClient(inObject);
					}

				} catch (IOException | ClassNotFoundException e) {
					try {
						joinSock.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		};

		Thread t = new Thread(task);
		t.start();

	}

	public void receiveLoginObj() {
		Runnable task = () -> {
			try {
				ObjectInputStream ois = new ObjectInputStream(loginSock.getInputStream());
				inObject = ois.readObject();

				if (inObject instanceof MyLogin) {
					receiveLogin(inObject);
				}

			} catch (IOException | ClassNotFoundException e) {
			}

		};

		Thread t = new Thread(task);
		t.start();

	}

	public void receiveMyInfoObj() {

		Runnable task = () -> {

			while (true) {
				try {
					ObjectInputStream ois = new ObjectInputStream(myInfoSock.getInputStream());
					inObject = ois.readObject();

					if (inObject instanceof RequestMyInfo) {
						receiveMyInfoId(inObject);
					}

				} catch (IOException | ClassNotFoundException e) {
					try {
						myInfoSock.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		};

		Thread t = new Thread(task);
		t.start();

	}

	public void receiveLogin(Object inO) throws ClassNotFoundException, IOException {

		boolean isInId = false;

		MemberDao memberDao = MemberDao.getInstance();
		memberMap = memberDao.getMemberMap();

		myLogin = (MyLogin) SerializationUtils.clone((MyLogin) inO);

		String inId = myLogin.getId();
		String inPw = myLogin.getPw();
		if (memberMap.get(inId) == null) {
		} else if (memberMap.get(inId) != null) {
			if (memberMap.get(inId).getPw().equals(inPw)) {
				isInId = true;
				// 클라이언트에서 로그인 한 ID를 저장하는 로직 필요
				InetAddress ia = chatSock.getInetAddress();
				int port = dsock.getLocalPort();
				InGameMemberId inMem = new InGameMemberId(inId, 0, ia, port);
				new ServerLobbyThreadIOController(this, inMem, chatSock, dsock);
			}
		}

		if (isInId) {
			sendMember(inId);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			loginSock.close();
			joinSock.close();
		} else {
			sendMember("fail");
		}

	}

	public void sendMember(String inId) {
		try {
			if (inId.equals("fail")) {
				String id = inId;
				String pw = inId;
				String name = inId;
				String addr = inId;
				String phone = inId;
				String eMail = inId;

				ObjectOutputStream oos = new ObjectOutputStream(loginSock.getOutputStream());

				MemberSerial member = new MemberSerial(id, pw, name, addr, phone, eMail);

				oos.writeObject(member);
				oos.flush();

			} else {
				String id = inId;
				String pw = memberMap.get(inId).getPw();
				String name = memberMap.get(inId).getName();
				String addr = memberMap.get(inId).getAddr();
				String phone = memberMap.get(inId).getPhone();
				String eMail = memberMap.get(inId).getEmail();

				ObjectOutputStream oos = new ObjectOutputStream(loginSock.getOutputStream());

				MemberSerial member = new MemberSerial(id, pw, name, addr, phone, eMail);

				oos.writeObject(member);
				oos.flush();
			}

		} catch (IOException e) {
		}
	}

	public void receiveIdDupleCheck(Object inO) throws ClassNotFoundException, IOException {

		boolean isNotInId = true;

		MemberDao memberDao = MemberDao.getInstance();
		memberMap = memberDao.getMemberMap();

		myId = (IdList) SerializationUtils.clone((IdList) inO);

		String inId = myId.getId();


		// for-each로 아이디 중복 조회

		for (String id : memberMap.keySet()) {
			if (id.equals(inId)) {
				isNotInId = false;
				break;
			}
		}


		if (isNotInId) {
			sendId(inId);
		} else {
			sendId("fail");
		}

	}

	public void sendId(String id) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(joinSock.getOutputStream());
			IdList myId = new IdList(id);

			oos.writeObject(myId);
			oos.flush();
		} catch (IOException e) {
		}

	}

	public void insertNewClient(Object inO) {

		MemberDao mDao = MemberDao.getInstance();
		MemberDto mDto = new MemberDto();

		member = (MemberSerial) SerializationUtils.clone((MemberSerial) inO);


		mDto.setId(member.getId());
		mDto.setPw(member.getPw());
		mDto.setName(member.getName());
		mDto.setAddr(member.getAddr());
		mDto.setPhone(member.getPhone());
		mDto.setEmail(member.getEmail());

		if (mDao.insertMember(mDto)) {
			System.out.println("회원 가입 완료");
		} else {
			System.out.println("회원 가입 실패");
		}

	}

	public void receiveMyInfoId(Object inO) {

		MemberDao memberDao = MemberDao.getInstance();
		memberMap = memberDao.getMemberMap();

		myIdInfo = (RequestMyInfo) SerializationUtils.clone((RequestMyInfo) inO);

		memberMap.get(myIdInfo.getId()).getName();

		String id = myIdInfo.getId();
		String pw = memberMap.get(id).getPw();
		String name = memberMap.get(id).getName();
		String addr = memberMap.get(id).getAddr();
		String phone = memberMap.get(id).getPhone();
		String eMail = memberMap.get(id).getEmail();

		try {

			ObjectOutputStream oos = new ObjectOutputStream(myInfoSock.getOutputStream());

			MemberSerial member = new MemberSerial(id, pw, name, addr, phone, eMail);

			oos.writeObject(member);
			oos.flush();

		} catch (IOException e) {
		}

	}

	public void sendInGameMemberMap() {

		Runnable task = () -> {

			while(true) {
				try {
					ArrayList<String> inUserList = new ArrayList<>();
					inUserList = ssj.getInUserList();

					ObjectOutputStream oos = new ObjectOutputStream(inGameListClient.getOutputStream());
					oos.writeObject(inUserList);
					oos.flush();
				} catch (IOException e) {
				}
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
			
		};

		Thread t = new Thread(task);
		t.start();
	}
	

}
