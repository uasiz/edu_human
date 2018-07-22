package DB.server.Setting;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import api.busalarm.dto.RouteDto;
import api.busalarm.dto.RouteorderDto;
import api.busalarm.dto.XmlDistanceGpsDto;


//nodeid와 nodenm 조인하는 것은 나중에 처리 요망

public class XMLParsing {
	
	
	
	
	
	
	ArrayList<RouteDto> BusCode(String url){//dto = BusCode
		//routeid, busno, endnodenm
	
	ArrayList<RouteDto> BC=new ArrayList<>();//정거장 목록
	final String USER_AGENT = "Mozilla/5.0";
	String routeid1;
	String routeno1;
	String endnodenm1;
	
	Node routeid = null;
	Node routeno = null;
	Node endnodenm = null;
	try {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.getResponseCode();
		
		InputStream is = null;
		InputStreamReader isr = null; 
		is = new URL(url).openStream();
		isr = new InputStreamReader(is, "utf-8");
		StringBuffer sb = new StringBuffer(); int c;
		while ((c = isr.read()) != -1) {sb.append((char) c);}
		isr.close(); is.close();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(new StringReader(sb.toString())));

		doc.getDocumentElement().normalize();

		Element body = (Element) doc.getElementsByTagName("body").item(0);
		Element items = (Element) body.getElementsByTagName("items").item(0);
		NodeList nList = doc.getElementsByTagName("item");
		 
		//버스코드(routeid), 버스번호(busno);
		
		for (int i = 0; i < nList.getLength(); i++) {
			Element item = (Element) items.getElementsByTagName("item").item(i);
			routeid = item.getElementsByTagName("routeid").item(0);
			routeno = item.getElementsByTagName("routeno").item(0);
			endnodenm = item.getElementsByTagName("endnodenm").item(0);

			routeid1= routeid.getChildNodes().item(0).getNodeValue();
			routeno1= routeno.getChildNodes().item(0).getNodeValue();
			endnodenm1= endnodenm.getChildNodes().item(0).getNodeValue();

			RouteDto dto = new RouteDto (routeid1, routeno1, endnodenm1);
			BC.add(dto);
		};
		
		for(RouteDto a:BC) {
			System.out.println(a);
		}
		System.out.println("저장된RouteDto 다 나왔어요~");
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return BC;

	}
	
	
	ArrayList<XmlDistanceGpsDto> StationCode(String url){//dto = BusCode
		//routeid, busno, endnodenm
	
	ArrayList<XmlDistanceGpsDto> BC=new ArrayList<>();//정거장 목록
	final String USER_AGENT = "Mozilla/5.0";
	String nodeid1;
	String nodenm1;
	String gpslati1;
	String gpslong1;
	
	Node gpslati = null;
	Node gpslong = null;
	Node nodeid = null;
	Node nodenm = null;
	try {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.getResponseCode();
		
		InputStream is = null;
		InputStreamReader isr = null; 
		is = new URL(url).openStream();
		isr = new InputStreamReader(is, "utf-8");
		StringBuffer sb = new StringBuffer(); int c;
		while ((c = isr.read()) != -1) {sb.append((char) c);}
		isr.close(); is.close();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(new StringReader(sb.toString())));

		doc.getDocumentElement().normalize();

		Element body = (Element) doc.getElementsByTagName("body").item(0);
		Element items = (Element) body.getElementsByTagName("items").item(0);
		NodeList nList = doc.getElementsByTagName("item");
		 
		
		
		for (int i = 0; i < nList.getLength(); i++) {
			Element item = (Element) items.getElementsByTagName("item").item(i);
			gpslati = item.getElementsByTagName("gpslati").item(0);
			gpslong = item.getElementsByTagName("gpslong").item(0);
			nodeid = item.getElementsByTagName("nodeid").item(0);
			nodenm = item.getElementsByTagName("nodenm").item(0);

			gpslati1= gpslati.getChildNodes().item(0).getNodeValue();
			gpslong1= gpslong.getChildNodes().item(0).getNodeValue();
			nodeid1= nodeid.getChildNodes().item(0).getNodeValue();
			nodenm1= nodenm.getChildNodes().item(0).getNodeValue();
			
			System.out.println(nodeid1);

			XmlDistanceGpsDto dto = new XmlDistanceGpsDto (gpslati1, gpslong1, nodeid1, nodenm1);
			BC.add(dto);
		};
		
		for(XmlDistanceGpsDto a:BC) {
			System.out.println(a);
		}
		System.out.println("저장된RouteDto 다 나왔어요~");
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return BC;

	}
	
	
	ArrayList<RouteorderDto> ordCode(String url){//dto = BusCode

	ArrayList<RouteorderDto> BC=new ArrayList<>();//정거장 목록
	final String USER_AGENT = "Mozilla/5.0";
	String routeid1;
	String nodeid1;
	String nodeord1;
	
	Node routeid = null;
	Node nodeid = null;
	Node nodeord = null;
	try {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.getResponseCode();
		
		InputStream is = null;
		InputStreamReader isr = null; 
		is = new URL(url).openStream();
		isr = new InputStreamReader(is, "utf-8");
		StringBuffer sb = new StringBuffer(); int c;
		while ((c = isr.read()) != -1) {sb.append((char) c);}
		isr.close(); is.close();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(new StringReader(sb.toString())));

		doc.getDocumentElement().normalize();

		Element body = (Element) doc.getElementsByTagName("body").item(0);
		Element items = (Element) body.getElementsByTagName("items").item(0);
		NodeList nList = doc.getElementsByTagName("item");
		 
		//버스코드(routeid), 버스번호(busno);
		
		for (int i = 0; i < nList.getLength(); i++) {
			Element item = (Element) items.getElementsByTagName("item").item(i);
			routeid = item.getElementsByTagName("routeid").item(0);
			nodeid = item.getElementsByTagName("nodeid").item(0);
			nodeord = item.getElementsByTagName("nodeord").item(0);
			
			routeid1= routeid.getChildNodes().item(0).getNodeValue();
			nodeid1= nodeid.getChildNodes().item(0).getNodeValue();
			nodeord1= nodeord.getChildNodes().item(0).getNodeValue();

			RouteorderDto dto = new RouteorderDto (routeid1, nodeid1, nodeord1);
			BC.add(dto);
			
		};
		
		for(RouteorderDto a:BC) {
			System.out.println(a);
		}
		System.out.println("저장된RouteDto 다 나왔어요~");
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return BC;

	}
}
