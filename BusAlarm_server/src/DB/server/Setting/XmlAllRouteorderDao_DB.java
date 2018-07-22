package DB.server.Setting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import api.busalarm.dto.RouteDto;
import api.busalarm.dto.RouteorderDto;
import api.busalarm.server.Setting;

public class XmlAllRouteorderDao_DB {
	Setting MyServer=new Setting();
	DataSource dataSource;
	
	public void createRouteord() {
		
	}
	
	
	public void inputroute(ArrayList<RouteorderDto> arr) { //버스목록 DB넣는 메서드
		String url= ("jdbc:oracle:thin:@"+MyServer.ip+":1521:orcl");
		Connection conn = null;
		PreparedStatement pst= null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, "human", "1111");
			
			for(RouteorderDto dto : arr) {
        	   
	           String query = "insert into routeorder (routeid, nodeid, nodeord) values(?,?,?)";
	           pst = conn.prepareStatement(query);
	           pst.setString(1,  dto.routeid); 
	           pst.setString(2,  dto.nodeid);
	           pst.setString(3,  dto.nodeord);
	           pst.executeUpdate();
           }

	           conn.commit();
		       conn.setAutoCommit(true);
		       System.out.println("정상적으로 db 에 넣었습니다.");
	        }catch (Exception e) {
	        	e.printStackTrace();
	        	System.out.println("도중에 오류가 났습니다. 실패!!");
	        }finally {
	        	System.out.println(arr.size()+"= arr size");
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
	
	ArrayList<String> searchBusCode() {
		
		ArrayList<String> dtos= new ArrayList<String>();
		Connection connection = null; //연결하는 것
		PreparedStatement preparedStatement = null; //커리 전송하는 것
		ResultSet resultSet = null; //결과물 받는 것
		
		String url= ("jdbc:oracle:thin:@"+MyServer.ip+":1521:orcl");
		
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, "human", "1111");
			String query = "select routeid from route";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String code= resultSet.getString("routeid");
				
				dtos.add(code);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				try {
					if(resultSet != null) resultSet.close();
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		return dtos;
		
	}
}

