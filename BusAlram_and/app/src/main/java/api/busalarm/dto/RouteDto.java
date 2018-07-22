package api.busalarm.dto;

import java.io.Serializable;

public class RouteDto implements Serializable{

	private static final long serialVersionUID = 6518456017050027897L;

	public String routeid, routeno, endnodenm;
	//http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteNoList?serviceKey=56%2FzakKOLYqE1OAN3nuMEE5%2FFr3pH1T%2FItWmS%2BBzQP5IZhW8odmAaxc%2FfS8m8L67ytXLjrip3cM%2FxlmDgHLCCQ%3D%3D&cityCode=34010&numOfRows=300
	//천안 모든 버스코드 조회
	//버스코드(id)(routeid), 버스번호(routeno), 목적지방향(endnodenm)

	@Override
	public String toString() {
		return "BDto [routeid=" + routeid + ", routeno=" + routeno + ", endnodenm=" + endnodenm + "]";

	}

	public RouteDto() {}
	public RouteDto(String routeid, String routeno, String endnodenm) {
		super();
		this.routeid = routeid;
		this.routeno = routeno;
		this.endnodenm = endnodenm;
	}

}