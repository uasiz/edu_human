package com.humanMind.client;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientNewSocket {

	Socket joinClient;
	Socket loginClient;
	Socket chatClient;
	Socket myInfoClient;
	Socket inGameListClient;
	DatagramSocket dsock;
	
	
	ClientNewSocket() {
		init();
	}

	public void init() {
		try {
			joinClient = new Socket("192.168.55.64", 6665);
			loginClient = new Socket("192.168.55.64", 6666);
			chatClient = new Socket("192.168.55.64", 6667);
			myInfoClient = new Socket("192.168.55.64", 6668);
			inGameListClient = new Socket("192.168.55.64", 6669);
			dsock = new DatagramSocket();
			new ClientLoginThread(joinClient, loginClient, chatClient, myInfoClient, inGameListClient, dsock);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
