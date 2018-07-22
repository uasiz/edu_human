package api.busalarm.server;

import java.util.ArrayList;

import api.busalarm.dao.RouteDao;
import api.busalarm.dto.RouteDto;
import api.busalarm.dto.RouteorderDto;
import api.busalarm.dto.XmlDistanceGpsDto;
import api.busalarm.dto.XmlStationDto;
import api.busalarm.dto.XmlStationsBusListDto;
import api.busalarm.log.log;
import api.busalarm.server.추상.DataControllerAB;

public class DataController extends DataControllerAB{
	Setting MyServer=new Setting();
	XMLParsing parsing=new XMLParsing();
	
	String url=null;
	
	ArrayList<XmlDistanceGpsDto> FindStations(String x,String y) {// 메소드 아. (위치 좌표) => 근처 정거장 목록
		url="http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getCrdntPrxmtSttnList?"
				+"ServiceKey="+MyServer.KEY+"&"
				+"gpsLati="+x+"&"
				+"gpsLong="+y;	
		System.out.println("FindStations :"+url);
		log.TraceLog(url);
		return parsing.StationList(url);
	}
	String TestFindStations(String nodeid) {// 메소드 아. (정류장코드) => 정류장의 좌표 (3)
		RouteDao dao = new RouteDao();
		return dao.Nodeid_NodeXY(nodeid);
	}
	ArrayList<XmlStationsBusListDto> StationsBusList(String nodeid) {// 메소드 아. (정류장id) => 정류장에 오는 버스목록+걸리는시간
		url="http://openapi.tago.go.kr/openapi/service/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList?"
				+"ServiceKey="+MyServer.KEY+"&"
				+"cityCode="+MyServer.cityCode+"&"
				+"nodeId="+nodeid;
		System.out.println("StationsBusList"+url);
		log.TraceLog(url);
		return parsing.StationsBusList(url);
	}
	String endStationCheck(String nodeid, String routeid) {// 정류장id, 버스code => 정류장id에 도착하는 버스id의 도착시간확인
		url="http://openapi.tago.go.kr/openapi/service/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList?"
				+"ServiceKey="+MyServer.KEY+"&"
				+"cityCode="+MyServer.cityCode+"&"
				+"nodeId="+nodeid;
		System.out.println("endStationCheck : "+url);
		log.TraceLog(url);
		return parsing.StationEndBusCheck(url, routeid);
	}
	ArrayList<RouteDto> RouteDirection(String BusNo) {// 메소드 유. (버스 번호) => 버스 양 노선 방면(종착역) 목록
		RouteDao dao=new RouteDao();
		log.TraceLog(BusNo);
		return dao.SelectBusCode(BusNo);
	}
	ArrayList<RouteorderDto> routeorder(String routeId) {// 메소드 아. (위치 좌표) => 근처 정거장 목록
		url="http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getRouteAcctoBusLcList?"
				+"ServiceKey="+MyServer.KEY+"&"
				+"cityCode="+MyServer.cityCode+"&"
				+"routeId="+routeId;
		System.out.println("routeorder 다니는 정류장url : "+url);
		
		ArrayList<String> checks =parsing.routeStationcheck(url);
		RouteDao dao=new RouteDao();
		log.TraceLog(url);
		
		return dao.RouteorderCheck(routeId, checks);
	}
}
