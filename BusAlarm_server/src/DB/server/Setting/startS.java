package DB.server.Setting;

import java.util.ArrayList;

import api.busalarm.dto.RouteDto;
import api.busalarm.dto.RouteorderDto;
import api.busalarm.dto.XmlDistanceGpsDto;
import api.busalarm.server.Setting;

public class startS {
	
	
	public static void main(String[] args) {
		
		try {
			//DBrouteInput();
			//DBstationInput();
			DBrouteordInput();
			
			//한뭉텅이 트렌젝션 말들까?
			
			//인터벌 만들 것
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
		
		/*for(XmlAllBusDto_DB test:arr)
			System.out.println(test.toString());*/
	}
	
	private static void DBrouteordInput() {
		// TODO Auto-generated method stub
		Setting server=new Setting();
		XMLParsing xp=new XMLParsing();
		
		//loop = busCode
		
		XmlAllRouteorderDao_DB codes = new XmlAllRouteorderDao_DB();
		ArrayList<String> loops = codes.searchBusCode();
		
		for(String loop : loops) {
			System.out.println("버스코드 :"+loop);
			String url = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?"
					+"serviceKey="+server.KEY+"&"
					+"cityCode="+server.cityCode+"&numOfRows=300"+"&"
					+"routeId="+loop;
			//RouteorderDto
			System.out.println(url);
			ArrayList<RouteorderDto> arr = xp.ordCode(url);
			XmlAllRouteorderDao_DB dao=new XmlAllRouteorderDao_DB();
			dao.inputroute(arr);
			
		}
	}

	

	public static void DBrouteInput() {
		
		Setting server=new Setting();
		XMLParsing xp=new XMLParsing();
		
		String url="http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteNoList?"
				+"serviceKey="+server.KEY+"&"
				+"cityCode="+server.cityCode+"&numOfRows=300"; //xml에서 numOfRows 갯수를 받아와 다시 세팅하는 방식?
		
		ArrayList<RouteDto> arr = xp.BusCode(url);
		
		XmlAllBusDao_DB dao=new XmlAllBusDao_DB();
		dao.inputroute(arr);
	}
	
public static void DBstationInput() {
		
		Setting server=new Setting();
		XMLParsing xp=new XMLParsing();
		
		String url="http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getSttnNoList?"
				+"serviceKey="+server.KEY+"&"
				+"cityCode="+server.cityCode+"&numOfRows=2812";
		
		ArrayList<XmlDistanceGpsDto> arr = xp.StationCode(url);
		
		XmlAllnodeDao_DB dao=new XmlAllnodeDao_DB();
		dao.inputnode(arr);
	}

	
}
