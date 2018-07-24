package com.humanMind.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAcceptClass {

	ServerSocket joinServer;
	ServerSocket loginServer;
	ServerSocket chatServer;  
	ServerSocket myInfoServer;
	ServerSocket inGameListServer;
	
	Socket joinClient;
	Socket loginClient;  	  
	Socket chatClient;
	Socket myInfoClient;
	Socket inGameListClient;
	
	DatagramSocket dsock = null;
	
	ServerThreadIOController loginThread; 
	
	ServerAcceptClass() {
		ServerStartJFrame.getInstance();
		serverSet();
	}
	
	public void serverSet() {
		try {
			
			myInfoServer = new ServerSocket();
			joinServer = new ServerSocket();
			loginServer = new ServerSocket();
			chatServer = new ServerSocket();
			inGameListServer = new ServerSocket();
			
			joinServer.bind(new InetSocketAddress("192.168.55.64", 6665));
			loginServer.bind(new InetSocketAddress("192.168.55.64", 6666));
			chatServer.bind(new InetSocketAddress("192.168.55.64", 6667));
			myInfoServer.bind(new InetSocketAddress("192.168.55.64", 6668));
			inGameListServer.bind(new InetSocketAddress("192.168.55.64", 6669));
			dsock = new DatagramSocket(6670);
			
			while(true) {
				joinClient = joinServer.accept();
				loginClient = loginServer.accept();  
				chatClient = chatServer.accept();
				myInfoClient = myInfoServer.accept();
				inGameListClient = inGameListServer.accept();
				loginThread = new ServerThreadIOController(joinClient, loginClient, chatClient, myInfoClient, inGameListClient, dsock);
			}
			
		} catch (IOException e) {
		}
	}
}
