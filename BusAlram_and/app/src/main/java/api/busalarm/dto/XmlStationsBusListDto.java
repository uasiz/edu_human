package api.busalarm.dto;

import java.io.Serializable;

public class XmlStationsBusListDto implements Serializable{// 버스번호를 입력하면 도착할 정거장들을 알려주는 dto
    private static final long serialVersionUID = 6518456017050027897L;

    public String arrprevstationcnt, arrtime, nodeid, nodenm, routeid, routeno;
    //http://openapi.tago.go.kr/openapi/service/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList?serviceKey=56%2FzakKOLYqE1OAN3nuMEE5%2FFr3pH1T%2FItWmS%2BBzQP5IZhW8odmAaxc%2FfS8m8L67ytXLjrip3cM%2FxlmDgHLCCQ%3D%3D&cityCode=34010&nodeId=CAB285000916
    //arrprevstationcnt 남은 정류소 횟수
    //arrtime 도착시간 (초)
    //nodeid 정류소id
    //nodenm 정류소이름
    //routeid 버스id
    //routeno 버스번호
    @Override
    public String toString() {
        return "BDto [arrprevstationcnt=" + arrprevstationcnt + ", arrtime=" + arrtime + ", nodeid=" + nodeid + ", nodenm=" + nodenm +"]";
    }


    public XmlStationsBusListDto() {}
    public XmlStationsBusListDto(String arrprevstationcnt, String arrtime, String nodeid, String nodenm, String routeid, String routeno) {
        super();
        this.arrprevstationcnt = arrprevstationcnt;
        this.arrtime = arrtime;
        this.nodeid = nodeid;
        this.nodenm = nodenm;
        this.routeid = routeid;
        this.routeno = routeno;
    }

}
