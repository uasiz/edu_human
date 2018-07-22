package api.busalarm.dto;

import java.io.Serializable;

public class XmlStationDto implements Serializable{// 버스번호를 입력하면 도착할 정거장들을 알려주는 dto
	private static final long serialVersionUID = 6518456017050027897L;
	
	public String routenm, nodeid, nodeord;
	//버스코드(id)(없음 버스번호만 routenm), 정류소코드(id)(nodeid), 정류소 순번(nodeord)
	//버스번호 입력시 다니는 버스의 정류장 출력
	//http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getRouteAcctoBusLcList?cityCode=34010&routeId=CAB285000005&ServiceKey=56%2FzakKOLYqE1OAN3nuMEE5%2FFr3pH1T%2FItWmS%2BBzQP5IZhW8odmAaxc%2FfS8m8L67ytXLjrip3cM%2FxlmDgHLCCQ%3D%3D

	@Override
	public String toString() {
		return "BDto [routenm=" + routenm + ", nodeid=" + nodeid + ", nodeord=" + nodeord + "]"; 	
	}
	
	
	public XmlStationDto() {}
	public XmlStationDto(String routenm, String nodeid, String nodeord) {
		super();
		this.routenm = routenm;
		this.nodeid = nodeid;
		this.nodeord = nodeord;
	}
}
