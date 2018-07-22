package DB.server.Setting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import api.busalarm.dto.RouteDto;
import api.busalarm.dto.XmlDistanceGpsDto;
import api.busalarm.server.Setting;

public class XmlAllBusDao_DB {
	Setting MyServer=new Setting();
	
	public void createRoute() {
		
		Connection conn=null;
		PreparedStatement pst= null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url= ("jdbc:oracle:thin:@"+MyServer.ip+":1521:orcl");
			conn = DriverManager.getConnection(url, "human", "1111");
			conn.setAutoCommit(false);

           
        	   
           String query = "insert into route (routeid, routeno, endnodenm) values(?,?,?)";
           pst = conn.prepareStatement(query);
           pst.executeUpdate();
           
           conn.commit();
	       conn.setAutoCommit(true);
           
		}catch (Exception e) {
        	e.printStackTrace();
        	
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
	
	public void inputroute(ArrayList<RouteDto> Arr) { //버스목록 DB넣는 메서드
		Connection conn=null;
		PreparedStatement pst= null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url= ("jdbc:oracle:thin:@"+MyServer.ip+":1521:orcl");
			conn = DriverManager.getConnection(url, "human", "1111");
			conn.setAutoCommit(false);

           for(RouteDto dto:Arr) {
        	   
           String query = "insert into route (routeid, routeno, endnodenm) values(?,?,?)";
           pst = conn.prepareStatement(query);
           pst.setString(1,  dto.routeid); 
           pst.setString(2,  dto.routeno);
           pst.setString(3,  dto.endnodenm);
           pst.executeUpdate();
           
           }

           conn.commit();
	       conn.setAutoCommit(true);
	       System.out.println("정상적으로 db 에 넣었습니다.");
           
        }catch (Exception e) {
        	e.printStackTrace();
        	System.out.println(Arr.size()+"= arr size");
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
