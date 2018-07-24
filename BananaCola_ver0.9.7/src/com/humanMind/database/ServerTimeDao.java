package com.humanMind.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServerTimeDao {

	private static ServerTimeDao instance = new ServerTimeDao();

	public static ServerTimeDao getInstance() {
		return instance;
	}

	private ServerTimeDao() {

	}

	Connection conn;
	Statement st;
	ResultSet rs;

	public boolean insertTime(ServerTimeDto dto) {

		boolean insertOk = false;

		try {

			conn = DbConn.getConnection();
			st = conn.createStatement();
			String query = String.format("insert into HM_SERVER_TIME values('%s', '%s', '%s')", dto.getStartTime(),
					dto.getEndTime(), dto.getTotalTime());
			st.executeQuery(query);
			insertOk = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return insertOk;
	}

	public ArrayList<ServerTimeDto> select() {

		ArrayList<ServerTimeDto> timeList = new ArrayList<>();

		try {

			conn = DbConn.getConnection();
			st = conn.createStatement();
			String query = "select * from HM_SERVER_TIME order by START_TIME";
			rs = st.executeQuery(query);

			while (rs.next()) {
				ServerTimeDto dto = new ServerTimeDto();
				dto.setStartTime(rs.getString("START_TIME"));
				dto.setEndTime(rs.getString("END_TIME"));
				dto.setTotalTime(rs.getString("TOTAL_TIME"));
				timeList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return timeList;
	}
}
