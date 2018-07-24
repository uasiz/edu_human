package com.humanMind.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {
	
	// Connection ΩÃ±€≈Ê ¡¶¿€, µø±‚»≠ « ø‰
	
	private static Connection dbConn;
	public static Connection getConnection() {
		if(dbConn==null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				String user = "park";
				String pw = "1111";
				dbConn = DriverManager.getConnection(url, user, pw);
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return dbConn;
	}
	
	public static void dbClose() {
		if(dbConn!=null) {
			try {
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
