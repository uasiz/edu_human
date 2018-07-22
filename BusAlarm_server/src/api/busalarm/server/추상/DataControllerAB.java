package api.busalarm.server.추상;


public abstract class DataControllerAB {
	
	//초기 추상메서드
	
	String BusToArrive(String StationId) {// 메소드 가.	(정거장 id) => 도착할 버스목록
		return null;
	}
	String StationToArrive(String BusNo) {// 메소드 나.	(버스 번호) => 곧 도착할 정거장 목록
		return null;
	}
	String FindStations(int x,int y) {// 메소드 아. (위치 좌표) => 근처 정거장 목록
		return null;
	}
	String StationRoutes(String BusNo) {// 메소드 우. (버스 번호) => 노선 정거장 목록
		return null;
	}
}
