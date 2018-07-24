package com.humanMind.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

public class ClientJoinThread {

	private Socket sock = null;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private Object inObject = null;
	private boolean isNotInId = false;
	
	private String id;
	
	private IdList idList;
	private ClientJoinJFrame main;
	
	private Map<String, MemberSerial> myMap = new HashMap<>();

	ClientJoinThread(Socket c) {
		this.sock = c;
		main = new ClientJoinJFrame(sock, this);
	}
	
	public void idSend(String inId) {
		
		receiveIdCheek();
		
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			IdList checkId = new IdList(inId);
			oos.writeObject(checkId);
			oos.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void receiveIdCheek() {
		Runnable task = () -> {
			
		try {
			ois = new ObjectInputStream(sock.getInputStream());
			inObject = ois.readObject();
			this.idList = (IdList) SerializationUtils.clone((IdList) inObject);
			
			if(!idList.getId().equals("fail") && !idList.getId().equals("")) {
				id = idList.getId(); 
				isNotInId = true;

			} else {
				isNotInId = false;
			}
				
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		};

		Thread t = new Thread(task);
		t.start();
		
	}
	
	public void sendNewClient(String id, String pw, String name, String addr, String phone, String eMail) {
		
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			MemberSerial member = new MemberSerial(id, pw, name, addr, phone, eMail);
			oos.writeObject(member);
			oos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean getFlag() {
		return this.isNotInId;
	}
	
	
}
