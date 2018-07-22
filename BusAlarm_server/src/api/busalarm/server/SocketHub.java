package api.busalarm.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import api.busalarm.dto.RouteDto;
import api.busalarm.dto.RouteorderDto;
import api.busalarm.dto.XmlDistanceGpsDto;
import api.busalarm.dto.XmlStationDto;
import api.busalarm.dto.XmlStationsBusListDto;

public class SocketHub {
	Setting MyServer=new Setting();
	DataController DC=new DataController();
	ServerSocket server;  
	Socket client;  
	private InputStream reMsg;
	private OutputStream sendMsg;
	
	SocketHub(){
		serverset();
	}
	public void serverset() {
		try {
			server = new ServerSocket();
			server.bind(new InetSocketAddress(MyServer.ip,MyServer.port));
			//질문, 서버셋할 때마다 MyServer 뉴생성자 만들어서 쓰는 것인가?
			while(true) {
				System.out.println("서버는 기다린다");
				client = server.accept();
				ClientListening();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void ClientListening() {
		streamSet();
	}
	private void streamSet() {
		try {
			reMsg = client.getInputStream();
            byte[] reBuffer = new byte[100];
            reMsg.read(reBuffer);
            String data="";
            data = new String(reBuffer);
            data = data.trim(); //trim 문자열 양끝의 공백을 없앰
            System.out.println(data+" = 클라이언트에서 보낸 데이터");	
            WhatWant(data);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void WhatWant(String data){
		try {
			String[] parts = data.split("/"); //split (토큰라이저, char 보다 단순하게 자르기 편한 메서드라서)
	        String firstData = parts[0];
	        String secondData = parts[1];
	        
			sendMsg = client.getOutputStream();
			
			ObjectOutputStream outS= new ObjectOutputStream(sendMsg);
	        
	        switch(firstData) {
	        case "GiveMeStation": //내 주위에 어떤 정류장이 있는지 보내줘 (2-1) api 사용하나 위경도 좌표 근처를 확인할 수 있다면 db처리로 가능
	        	System.out.println("2-1");
	        	String[] xy = secondData.split(",");
	        	String x= xy[0];
	        	String y= xy[1];
	        	ArrayList<XmlDistanceGpsDto> WhereStation =DC.FindStations(x, y);
	        	for(XmlDistanceGpsDto dto: WhereStation)
	        		System.out.println(dto.toString() + " = server 에서 보낸 데이터");
	        	outS.writeObject(WhereStation);
	        	break;
	        case "GiveMeStationTest": //정류장 코드 보내줄테니 정류장 좌표 보내줘 //db로 처리함 (1-4, 2-4)
	        	System.out.println("1-4, 2-4");
	        	String GiveMeStationTest=DC.TestFindStations(secondData);
	        	System.out.println(GiveMeStationTest + " = server 에서 보낸 데이터");
	        	outS.writeObject(GiveMeStationTest);
	        	//writeBytes
	        	break;
	        case "GiveMeBus": //버스정류장 클릭 버스 번호 도착시간 출력 //api로 처리함 (1-3, 2-2)
	        	System.out.println("1-3, 2-2");
	        	ArrayList<XmlStationsBusListDto> StationsBusList=DC.StationsBusList(secondData);
	        	outS.writeObject(StationsBusList); 
	        	break;
	    
	        case "RouteRoute":// 버스코드를 보내줄테니 버스의 노선정보(모든정류장과 버스다니는 정류장) 보내줘 (1-2,2-3)
	        	System.out.println("1-2, 2-3");
	        	ArrayList<RouteorderDto> oneBusList=DC.routeorder(secondData);
	        	outS.writeObject(oneBusList); 
	        	
	        	break;
	        case "RouteDirection":// 버스번호 줄테니 버스의 방면을 보내줘 (1-1)
	        	System.out.println("1-1");
	  		  	ArrayList<RouteDto> RouteId=DC.RouteDirection(secondData);//3번 버스의 노선을 보여준다.
	  		  	for(RouteDto dto : RouteId)
	  		  		System.out.println(dto+" = server 에서 보낸 데이터");
	  		  	outS.writeObject(RouteId);
	        	break;
	        case "EndBusCheck": //nodeid 의 routeid가 120초 이내에 접근했는지 확인 (1-5, 2-5)
	        	System.out.println("1-5, 2-5");
	        	String[] endNode = secondData.split(",");
	        	String nodeid = endNode[0];
	        	String routeid= endNode[1];
	        	String check =","+ DC.endStationCheck(nodeid, routeid);
	        	//int check1 = Integer.parseInt(check);
	        	System.out.println("안되면 말고"+check);
	        	outS.writeObject(check);
	        	//sendMsg.write(check1);
	        }
	        outS.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(ArrayIndexOutOfBoundsException e) {//split 했을때 '/' 뒤에 데이터가 없을때
	         System.out.println("클라이언트에서 보낸 값이 잘못되었습니다.");
		}
	}
}