package com.humanMind.client;

import java.io.Serializable;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InGameMemberId implements Serializable {
	
	private String id;
	private int location;
	private int port;
	private InetAddress ia;
	
	public InGameMemberId() {
		
	}
	
	public InGameMemberId(String id, String ia, int port) throws UnknownHostException {
		super();
		this.id = id;
		this.ia = InetAddress.getByName(ia);
		this.port = port;
	}

	
	public InGameMemberId(String id, int location, InetAddress ia, int port) {
		super();
		this.id = id;
		this.location = location;
		this.ia = ia;
		this.port = port;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	public InetAddress getIa() {
		return ia;
	}

	public int getPort() {
		return port;
	}
	
}
