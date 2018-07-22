package api.busalarm.dto;

import java.io.Serializable;


public class XmlDistanceGpsDto implements Serializable {
	
	private static final long serialVersionUID = 6518456017050027897L;
	
	public String nodeid, nodenm, gpslati, gpslong;
	
	public String gpslatime, gpslongme;
	//좌표값이니 gpsliati 공용으로 써도 되지 않을러나?
	
	//http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getSttnNoList?serviceKey=%2FxuL5UkqYV%2FUIpdiMlpnAezFY8%2FWWJPLJWaHA%2BM%2FhVYa4MZS4oLoACbV5SnyiUUYX%2BgZoBOmogMn7ORqi9dQKg%3D%3D&cityCode=34010&numOfRows=2812
	//정류소코드(id)(nodeid), 정류소 이름(nodenm), 정류소x좌표(gpslati), 정류소y좌표(gpslong)
	


	@Override
	public String toString() {
		return "BDto [nodeid=" + nodeid + ", nodenm=" + nodenm + ", gpslati=" + gpslati + ", gpslong=" + gpslong + "]"; 
				
	}
	
	
	public XmlDistanceGpsDto() {}
	public XmlDistanceGpsDto(String gpslati, String gpslong,String nodeid, String nodenm) {
		super();
		this.nodeid = nodeid;
		this.nodenm = nodenm;
		this.gpslati = gpslati;
		this.gpslong = gpslong;
	}
	
	public XmlDistanceGpsDto(String gpslatime, String gpslongme) {
		super();
		this.gpslatime = gpslatime;
		this.gpslongme = gpslongme;
	}
	
	
	
}