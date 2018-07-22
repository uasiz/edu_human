package api.busalarm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import api.busalarm.dto.RouteDto;
import api.busalarm.dto.RouteorderDto;
import api.busalarm.server.Setting;

public class RouteDao {
	
	public void set() {
		
	}
	
	public String Nodeid_NodeXY(String nodeid) { //정류장 id를 주면 좌표 주기
		
		System.out.println("노드아이디가 들어왔나요?"+nodeid);
		String gps=null;
		Setting MyServer=new Setting();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url= ("jdbc:oracle:thin:@"+MyServer.ip+":1521:orcl");
			Connection conn = DriverManager.getConnection(url, MyServer.db_id, MyServer.db_password);
			PreparedStatement pst= null;
			ResultSet resultSet = null; 
			String query =
					"select * from node where nodeid=?";
           pst = conn.prepareStatement(query);
           pst.setString(1,  nodeid);
           resultSet = pst.executeQuery();
           
           while(resultSet.next()) {

               String gpslati= resultSet.getString("gpslati");
               String gpslong= resultSet.getString("gpslong");
               
               gps= ","+gpslati+","+gpslong;
               
               System.out.println("db에서 뺏어요"+gps);
           }
 
           if(resultSet != null) resultSet.close();
           if(pst != null) pst.close();
           if(conn != null) conn.close();
           
        }catch (Exception e) {
        	e.printStackTrace();
        } 
        return gps;		
	}
	
	public ArrayList<RouteorderDto> RouteorderCheck(String routeidin, ArrayList<String> checks) { 
		ArrayList<RouteorderDto> dtos=new ArrayList<>();
		boolean check = false;
		Setting MyServer=new Setting();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url= ("jdbc:oracle:thin:@"+MyServer.ip+":1521:orcl");
			Connection conn = DriverManager.getConnection(url, MyServer.db_id, MyServer.db_password);
			PreparedStatement pst= null;
			ResultSet resultSet = null; 
        
			String query =
					"select routeorder.nodeid, node.nodenm, routeorder.nodeord, routeorder.routeid from ROUTEORDER inner join node on routeorder.nodeid=node.nodeid where ROUTEORDER.ROUTEID=? order by TO_NUMBER(routeorder.nodeord) asc";	
           pst = conn.prepareStatement(query);
           pst.setString(1,  routeidin);
           resultSet = pst.executeQuery();

           while(resultSet.next()) {
               String nodeid= resultSet.getString("nodeid");
               String nodenm= resultSet.getString("nodenm");
               String nodeord= resultSet.getString("nodeord");
               RouteorderDto dto = new RouteorderDto (nodeid, nodenm, nodeord, routeidin, check);
 
               for (int j=0; j<checks.size(); j++) {					
					if(nodeord.equals(checks.get(j))) {
						dto.check = true;
						checks.remove(j);
					}
				}
               dtos.add(dto);
            }
           if(resultSet != null) resultSet.close();
           if(pst != null) pst.close();
           if(conn != null) conn.close();
           
        }catch (Exception e) {
        	e.printStackTrace();
        } 
		return dtos; 
	}
	
	
	public ArrayList<RouteDto> SelectBusCode(String BusNo) { //버스번호를 입력받으면 코드가 리턴되는 메서드
		ArrayList<RouteDto> dtos=new ArrayList<>();
		Setting MyServer=new Setting();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url= ("jdbc:oracle:thin:@"+MyServer.ip+":1521:orcl");
			Connection conn = DriverManager.getConnection(url, MyServer.db_id, MyServer.db_password);
			PreparedStatement pst= null;
			ResultSet resultSet = null; 

           //routeid 버스코드
           //busno 버스번호
        
			String query = "select * from route where routeno=?";
					
           //String query = "select * from route where routeno=?"; 
           pst = conn.prepareStatement(query);
           pst.setString(1,  BusNo);
           resultSet = pst.executeQuery();
           
           
           /*
   		select * from node where nodeid in(
   		    select nodeid from (
   		        select *from routeorder where routeid in(select routeid from (select rownum as rn,routeid from (select *from route where routeno='11'))where rn='2')
   		    )where nodeord in(
   		        select max(TO_NUMBER(nodeord))from routeorder where routeid in(select routeid from (select rownum as rn,routeid from (select *from route where routeno='11'))
   		        where rn='2')))
   		or nodeid in(
   		    select nodeid from (
   		        select *from routeorder where routeid in(select routeid from (select rownum as rn,routeid from (select *from route where routeno='11'))where rn='1')
   		    )where nodeord in(
   		        select max(TO_NUMBER(nodeord))from routeorder where routeid in(select routeid from (select rownum as rn,routeid from (select *from route where routeno='11'))
   		        where rn='1')
   		    ));
   		*/
           
           
           

           while(resultSet.next()) {
               String routeid= resultSet.getString("routeid");
               String routeno= resultSet.getString("routeno");
               String endnodenm= resultSet.getString("endnodenm");
            
               RouteDto dto = new RouteDto (routeid, routeno, endnodenm);
               dtos.add(dto);
            }
           System.out.println("db에서 뺏어요~종점정류장주기");
           if(resultSet != null) resultSet.close();
           if(pst != null) pst.close();
           if(conn != null) conn.close();
           
        }catch (Exception e) {
        	e.printStackTrace();
        } 
		return dtos; 
	}
	
	/*public ArrayList<RouteDto> oneBusEndStation(String BusNo) { //버스번호를 입력받으면 코드가 리턴되는 메서드
		ArrayList<RouteDto> dtos=new ArrayList<>();
		Setting MyServer=new Setting();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url= ("jdbc:oracle:thin:@"+MyServer.ip+":1521:orcl");
			Connection conn = DriverManager.getConnection(url, MyServer.db_id, MyServer.db_password);
			PreparedStatement pst= null;
			ResultSet resultSet = null; 

           //routeid 버스코드
           //busno 버스번호
        	   
           String query1 = "select station.stationname, route.routeid, route.busno from station, route where station.STATIONID in(" + 
           		"select stationid from routeorder where NODEORD in(" + 
           		"select max(nodeord)from routeorder where ROUTEID in(" + 
           		"select routeid from route where busno=?"; 
           pst = conn.prepareStatement(query1);
           pst.setString(1,  BusNo);
           resultSet = pst.executeQuery();

           while(resultSet.next()) {//3번 버스의 마지막 정류장 출력
        	   //버스id에 맞춰서 <= endstaion 추가하여 (객체)
        	   //Arraylist인 dtos에 넣기
               String routeid= resultSet.getString("routeid");
               String busno= resultSet.getString("busno");
               String endnodenm= resultSet.getString("stationname");
               RouteDto dto = new RouteDto (routeid, busno, endnodenm);
               dtos.add(dto);
            }
           if(resultSet != null) resultSet.close();
           if(pst != null) pst.close();
           if(conn != null) conn.close();
           
        }catch (Exception e) {
        	e.printStackTrace();
        } 
		return dtos; 
	}*/
}
