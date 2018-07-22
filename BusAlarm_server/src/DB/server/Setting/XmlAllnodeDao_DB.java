package DB.server.Setting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import api.busalarm.dto.RouteDto;
import api.busalarm.dto.RouteorderDto;
import api.busalarm.dto.XmlDistanceGpsDto;
import api.busalarm.server.Setting;

public class XmlAllnodeDao_DB {
	Setting MyServer=new Setting();
	
	public void createNode() {
		
	}
	
	public void inputnode(ArrayList<XmlDistanceGpsDto> arr) { //버스목록 DB넣는 메서드
		String url= ("jdbc:oracle:thin:@"+MyServer.ip+":1521:orcl");
		Connection conn = null;
		PreparedStatement pst= null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "human", "1111");
			//http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?serviceKey=56%2FzakKOLYqE1OAN3nuMEE5%2FFr3pH1T%2FItWmS%2BBzQP5IZhW8odmAaxc%2FfS8m8L67ytXLjrip3cM%2FxlmDgHLCCQ%3D%3D&numOfRows=41&pageSize=10&pageNo=1&startPage=1&cityCode=34010&routeId=CAB285000004
			conn.setAutoCommit(false);
			//int i=0;
			for(XmlDistanceGpsDto dto : arr) {
	           String query = "insert into node (gpslati, gpslong, nodeid, nodenm) values(?,?,?,?)";
	           pst = conn.prepareStatement(query);
	           pst.setString(1,  dto.gpslati); 
	           pst.setString(2,  dto.gpslong);
	           pst.setString(3,  dto.nodeid);
	           pst.setString(4,  dto.nodenm);
	           //System.out.println(i+++"번쨰 : "+dto.toString());
	           pst.executeUpdate();
			}
			conn.commit();
	        conn.setAutoCommit(true);
			System.out.println("정상적으로 db 에 넣었습니다.");
        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("도중에 오류가 났습니다. 실패!!");
        }finally {
				try {
					if(pst != null) pst.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				try {
					if(conn != null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
        }
              
	} 
}
