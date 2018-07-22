package api.busalarm.dto;

import java.io.Serializable;

public class RouteorderDto implements Serializable{

    private static final long serialVersionUID = 6518456017050027897L;

    public String nodeid, nodenm, nodeord, routeid;

    public boolean check=false;
    //http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?serviceKey=56%2FzakKOLYqE1OAN3nuMEE5%2FFr3pH1T%2FItWmS%2BBzQP5IZhW8odmAaxc%2FfS8m8L67ytXLjrip3cM%2FxlmDgHLCCQ%3D%3D&numOfRows=41&pageSize=10&pageNo=1&startPage=1&cityCode=34010&routeId=CAB285000004
    //천안 one 버스 모든정류장 조회
    //버스코드(id)(routeid), 정류장코드(stationid), 정류장순번(nodeord)


    @Override
    public String toString() {
        return "BDto [routeid=" + routeid + ", nodenm :" + nodenm + ", nodeid=" + nodeid + "nodeord" + nodeord + "]";

    }

    public RouteorderDto() {}
    public RouteorderDto(String routeid, String stationid, String routeord) {
        super();
        this.routeid = routeid;
        this.nodeid = stationid;
        this.nodeord = routeord;
    }
    public RouteorderDto(String nodeid, String nodenm, String routeord, String routeid) {
        super();
        this.nodeid = nodeid;
        this.nodenm = nodenm;
        this.nodeord = routeord;
        this.routeid = routeid;
    }
    public RouteorderDto(String nodeid, String nodenm, String routeno, String routeid, Boolean check) {
        super();
        this.nodeid = nodeid;
        this.nodenm = nodenm;
        this.nodeord = routeno;
        this.routeid = routeid;
        this.check = false;
    }

}