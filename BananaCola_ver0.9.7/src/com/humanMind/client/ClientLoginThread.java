package com.humanMind.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.humanMind.client.IdList;
import com.humanMind.client.MemberSerial;
import com.humanMind.client.MyLogin;
import com.humanMind.database.MemberDao;
import com.humanMind.database.MemberDto;

public class ClientLoginThread {

	private Socket loginSock;
	private Socket chatSock;
	private Socket joinSock;
	private Socket myInfoSock;
	private Socket inGameListSock;
	private DatagramSocket dsock;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	private Object inObject = null;
	private int port = 0;
	private MyLogin myLogin = new MyLogin();
	private MemberSerial myInfo = new MemberSerial();

	private String id = "";

	private Map<String, MemberSerial> myMap = new HashMap<>();

	ClientLoginThread(Socket j, Socket l, Socket c, Socket m, Socket i, DatagramSocket d) {
		this.joinSock = j;
		this.loginSock = l;
		this.chatSock = c;
		this.myInfoSock = m;
		this.inGameListSock = i;
		this.dsock = d;
		new ClientMainJFrame(joinSock, loginSock, chatSock, myInfoSock, inGameListSock, dsock, this);
	}

	public void receiveObject() {

		Runnable task = () -> {
			
			try {
				ois = new ObjectInputStream(loginSock.getInputStream());
				inObject = ois.readObject();

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				if (inObject instanceof MemberSerial) {
					setMyMap(inObject);
				} else {
					System.out.println("변환할 수 없습니다.");
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};

		Thread t = new Thread(task);
		t.start();

	}

	public void checkLoginAccount(String tempId, String tempPw) {

		try {
			oos = new ObjectOutputStream(loginSock.getOutputStream());
			myLogin = new MyLogin(tempId, tempPw);

			oos.writeObject(myLogin);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		receiveObject();
	}

	public void setMyMap(Object inO) throws ClassNotFoundException, IOException {

		MemberSerial member = (MemberSerial) SerializationUtils.clone((MemberSerial) inO);
		id = member.getId();
		myMap.put(id, member);

	}

	public Map<String, MemberSerial> getMyMap() {
		return this.myMap;
	}

	public String getId() {
		return this.id;
	}

}
