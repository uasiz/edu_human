package api.busalarm.server;

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
import api.busalarm.dto.XmlStationDto;
import api.busalarm.dto.XmlStationsBusListDto;


public class XMLParsing {
	final String USER_AGENT = "Mozilla/5.0";
	URL obj=null;
	HttpURLConnection con=null;
	NodeList nList=null;
	Element items=null;
	Element body=null;	
	
	ArrayList<XmlDistanceGpsDto> StationList(String url) {//dto = node ord
		ArrayList<XmlDistanceGpsDto> arr=new ArrayList<>();//정거장 목록
		
		String nodeid1;
		String nodenm1;
		String gpslati1;
		String gpslong1;
		
		Node nodeid = null;
		Node nodenm = null;
		Node gpslati = null;
		Node gpslong = null;
		
		set(url);
		System.out.println("엔리스트의 길이 :"+nList.getLength());
			//정류소코드(id)(nodeid), 정류소 이름(nodenm), 정류소x좌표(gpslati), 정류소y좌표(gpslong)
		for (int i = 0; i < nList.getLength(); i++) {
				Element item = (Element) items.getElementsByTagName("item").item(i);
				nodeid = item.getElementsByTagName("nodeid").item(0);
				nodenm = item.getElementsByTagName("nodenm").item(0);
				gpslati = item.getElementsByTagName("gpslati").item(0);
				gpslong = item.getElementsByTagName("gpslong").item(0);
				
				nodeid1= nodeid.getChildNodes().item(0).getNodeValue();
				nodenm1= nodenm.getChildNodes().item(0).getNodeValue();
				gpslati1= gpslati.getChildNodes().item(0).getNodeValue();
				gpslong1= gpslong.getChildNodes().item(0).getNodeValue();
				
				//nodeid, nodenm, gpslati, gpslong
	
				XmlDistanceGpsDto dto = new XmlDistanceGpsDto (gpslati1, gpslong1, nodeid1, nodenm1);
				arr.add(dto);
		}
		return arr;
	}
	
	String StationListTest(String url, String nodeidsearch) {//dto = node ord

		String gpslati1;
		String gpslong1;
		String nodeid1;
		
		Node gpslati = null;
		Node gpslong = null;
		Node nodeid = null;
		
		int t = 0;
		
		set(url);
		
		System.out.println(nList.toString());
		System.out.println("엔리스트의 길이 :"+nList.getLength());//10
		
		for (int i = 0; i < nList.getLength(); i++) {
			Element item = (Element) items.getElementsByTagName("item").item(i);
			nodeid = item.getElementsByTagName("nodeid").item(0);
			nodeid1= nodeid.getChildNodes().item(0).getNodeValue();
			System.out.println("포문은 돌아요~");
			
			if(nodeid1.equals(nodeidsearch)) {
				t=i;
				System.out.println("노드코드 :"+nodeid1+"////입력받은 노드코드 :"+nodeidsearch);
				System.out.println(t);
				break;
			}
		}
		
		System.out.println("포문나왔다 :"+t);
		Element item = (Element) items.getElementsByTagName("item").item(t);
				
				gpslati = item.getElementsByTagName("gpslati").item(0);
				gpslong = item.getElementsByTagName("gpslong").item(0);
				
				
				gpslati1= gpslati.getChildNodes().item(0).getNodeValue();
				gpslong1= gpslong.getChildNodes().item(0).getNodeValue();
				
				
				
				String test= ","+gpslati1+","+gpslong1;
				System.out.println("dao 값 :"+test);
		
		return test;
	}
	
	ArrayList<XmlStationsBusListDto> StationsBusList(String url) {//dto = node ord
		ArrayList<XmlStationsBusListDto> arr=new ArrayList<>();//정거장 목록
	
		String arrprevstationcnt1;
		String arrtime1;
		String nodeid1;
		String nodenm1;
		String routeid1;
		String routeno1;
		
		Node arrprevstationcnt = null;
		Node arrtime = null;
		Node nodeid = null;
		Node nodenm = null;
		Node routeid = null;
		Node routeno = null;
		
		set(url);
		System.out.println("엔리스트의 길이 :"+nList.getLength());
			//정류소코드(id)(nodeid), 정류소 이름(nodenm), 정류소x좌표(gpslati), 정류소y좌표(gpslong)
		for (int i = 0; i < nList.getLength(); i++) {
				Element item = (Element) items.getElementsByTagName("item").item(i);
				arrprevstationcnt = item.getElementsByTagName("arrprevstationcnt").item(0);
				arrtime = item.getElementsByTagName("arrtime").item(0);
				nodeid = item.getElementsByTagName("nodeid").item(0);
				nodenm = item.getElementsByTagName("nodenm").item(0);
				routeid = item.getElementsByTagName("routeid").item(0);
				routeno = item.getElementsByTagName("routeno").item(0);
				
				arrprevstationcnt1= arrprevstationcnt.getChildNodes().item(0).getNodeValue();
				arrtime1= arrtime.getChildNodes().item(0).getNodeValue();
				nodeid1= nodeid.getChildNodes().item(0).getNodeValue();
				nodenm1= nodenm.getChildNodes().item(0).getNodeValue();
				routeid1= routeid.getChildNodes().item(0).getNodeValue();
				routeno1= routeno.getChildNodes().item(0).getNodeValue();
				
				//nodeid, nodenm, gpslati, gpslong
	
				XmlStationsBusListDto dto = new XmlStationsBusListDto (arrprevstationcnt1, arrtime1, nodeid1, nodenm1, routeid1, routeno1);
				arr.add(dto);
		}
		return arr;
	}
	
	String StationEndBusCheck(String url, String busCode) {//dto = node ord

		String check = "0";

		String arrtime1;
		String routeid1;

		Node arrtime = null;
		Node routeid = null;
		
		
		set(url);
		System.out.println("엔리스트의 길이 :"+nList.getLength());
		for (int i = 0; i < nList.getLength(); i++) {
				Element item = (Element) items.getElementsByTagName("item").item(i);
				arrtime = item.getElementsByTagName("arrtime").item(0);
				routeid = item.getElementsByTagName("routeid").item(0);
				
				arrtime1= arrtime.getChildNodes().item(0).getNodeValue();
				routeid1= routeid.getChildNodes().item(0).getNodeValue();
				
				if(routeid1.equals(busCode)) {
					//정거장 개수로? if(Integer.parseInt(arrprevstationcnt)<2){
					if(Integer.parseInt(arrtime1)<300) {
						check= "1";
					}
				}
				
		}
		return check;
	}
	
	
	
	ArrayList<RouteorderDto> routeorder(String url, String url2) {
		ArrayList<RouteorderDto> arr=new ArrayList<>();//버스 노선의 모든 정거장 출력
		
		String nodeid1;
		String nodenm1;
		String nodeord1;
		String routeid1;
		boolean check = false;
		
		Node nodeid = null;
		Node nodenm = null;
		Node nodeord = null;
		Node routeid = null;
		
		ArrayList<String> arr2 = routeStationcheck(url2);
		
		set(url);
		System.out.println("엔리스트의 길이 :"+nList.getLength());
			//정류소코드(nodeid), 정류소 이름(nodenm), 정류소 순번(nodeord), 버스코드(routeid)
		System.out.println(nList.getLength());
		
		for (int i = 0; i < nList.getLength(); i++) {
				Element item = (Element) items.getElementsByTagName("item").item(i);
				nodeid = item.getElementsByTagName("nodeid").item(0);
				nodenm = item.getElementsByTagName("nodenm").item(0);
				nodeord = item.getElementsByTagName("nodeord").item(0);
				routeid = item.getElementsByTagName("routeid").item(0);
				
				nodeid1= nodeid.getChildNodes().item(0).getNodeValue();
				nodenm1= nodenm.getChildNodes().item(0).getNodeValue();
				nodeord1= nodeord.getChildNodes().item(0).getNodeValue();
				routeid1= routeid.getChildNodes().item(0).getNodeValue();
				
				RouteorderDto dto = new RouteorderDto (nodeid1, nodenm1, nodeord1, routeid1, check);
				
				for (int j=0; j<arr2.size(); j++) {					
					if(nodeord1.equals(arr2.get(j))) {
						System.out.println("체크들어옴 : "+arr2.get(j));
						dto.check = true;
						System.out.println("체크 변경 함 : "+nodeord1+dto.check);
						arr2.remove(j);
					}
				}
				arr.add(dto);			
		}
		return arr;
	}
	private void set(String url) {
		try {
		obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
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
		items = (Element) body.getElementsByTagName("items").item(0);
		nList = doc.getElementsByTagName("item");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	ArrayList<String> routeStationcheck(String url) { 
		ArrayList<String> arr2=new ArrayList<>();
		String nodeord1a;
		Node nodeorda = null;
		set(url);
		for (int i = 0; i < nList.getLength(); i++) {
				Element item = (Element) items.getElementsByTagName("item").item(i);
				nodeorda = item.getElementsByTagName("nodeord").item(0);
				nodeord1a= nodeorda.getChildNodes().item(0).getNodeValue();
				arr2.add(nodeord1a);
		}
		return arr2;
	}
	
}
