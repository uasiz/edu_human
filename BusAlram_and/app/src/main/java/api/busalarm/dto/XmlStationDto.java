package api.busalarm.dto;

import java.io.Serializable;

public class XmlStationDto implements Serializable{// 버스번호를 입력하면 도착할 정거장들을 알려주는 dto
	private static final long serialVersionUID = 6518456017050027897L;
	
	public String routenm, nodeid, nodeord;
	//버스코드(id)(없음 버스번호만 routenm), 정류소코드(id)(nodeid), 정류소 순번(nodeord)
	
	public String input1, input2;

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
	
	public XmlStationDto(String input1, String input2) {
		super();
		this.input1= input1;
		this.input2= input2;
	}
	
	
}
