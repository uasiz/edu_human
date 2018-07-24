package com.humanMind.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.humanMind.client.IdList;
import com.humanMind.client.MemberSerial;
import com.humanMind.client.MyLogin;
import com.humanMind.database.MemberDao;
import com.humanMind.database.MemberDto;

public class ClientMyInfoThread {

	private Socket sock;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private Object inObject = null;
	
	private RequestMyInfo myId = new RequestMyInfo();
	private MemberSerial myInfo = new MemberSerial();

	private String id = "";

	private Map<String, MemberSerial> myMap = new HashMap<>();

	ClientMyInfoThread(String id, Socket c) {
		this.id = id;
		this.sock = c;
		sendMyId(id);
	}

	public void receiveObject() {
		
		Runnable task = () -> {
		
			try {
				ois = new ObjectInputStream(sock.getInputStream());
				inObject = ois.readObject();
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				if (inObject.getClass().equals(myInfo.getClass())) {
					setMyMap(inObject);
				} else if (inObject.getClass() == null) {
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

	public void sendMyId(String tempId) {
		
		receiveObject();
		
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			myId = new RequestMyInfo(tempId);
			
			oos.writeObject(myId);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void setMyMap(Object inO) throws ClassNotFoundException, IOException {

		MemberSerial member = (MemberSerial) SerializationUtils.clone((MemberSerial) inO);
		id = member.getId();
		myMap.put(id, member);

		new ClientMyInfoJFrame(id, myMap);
	}

	public Map<String, MemberSerial> getMyMap() {
		return this.myMap;
	}

	public String getId() {
		return this.id;
	}

	public Socket getSocket() {
		return this.sock;
	}
}
