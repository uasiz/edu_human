package com.humanMind.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDao {
	
	private Map<String, MemberDto> memberMap = new HashMap<>();
	
	public static MemberDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public MemberDao() {
		select();
	}
	
	private static class LazyHolder {
		private static final MemberDao INSTANCE = new MemberDao();
	}
	
	
	Connection conn;
	Statement st;
	ResultSet rs;
	
	public synchronized Map<String, MemberDto> getMemberMap() {
		return memberMap;
	}
	
	public boolean insertMember(MemberDto dto) {
		
		boolean insertOk = false;
		
		try {

			conn = DbConn.getConnection();
			st = conn.createStatement();
			String query = String.format("insert into HM_MEMBER values('%s', '%s', '%s', '%s', '%s', '%s')", 
					dto.getId(), dto.getPw(), dto.getName(), dto.getAddr(), dto.getPhone(), dto.getEmail());
			st.executeUpdate(query);
			
			insertOk = true; 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				select();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return insertOk;
	}
	
	
	public void select() {

		try {

			conn = DbConn.getConnection();
			st = conn.createStatement();
			String query = "select * from HM_MEMBER order by ID";
			rs = st.executeQuery(query);

			memberMap.clear();
			
			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				
				memberMap.put(rs.getString("id"), dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public ArrayList<MemberDto> selectToArrayList() {

		ArrayList<MemberDto> memberList = new ArrayList<>();
		
		try {

			conn = DbConn.getConnection();
			st = conn.createStatement();
			String query = "select * from HM_MEMBER order by ID";
			rs = st.executeQuery(query);

			memberMap.clear();
			
			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				
				memberList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return memberList;

	}
	
	
	public MemberDto select(String id) {

		MemberDto dto = memberMap.get(id);

		return dto;
	}
	
	
	
	public boolean update(MemberDto dto) {
		
		boolean updateOk = false;
				
		try {

			conn = DbConn.getConnection();
			st = conn.createStatement();

			String query = String.format("update HM_MEMBER set name = '%s', phone = '%s', email = '%s', addr = '%s' where id = '%s'", 
					dto.getName(), dto.getPhone(), dto.getEmail(), dto.getAddr(), dto.getId());
			st.executeUpdate(query);

			select(); 
			
			updateOk = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				select();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return updateOk;
	}
	
	
	public boolean delete(String inId) {
		
		boolean deleteOk = false;
		
		try {

			conn = DbConn.getConnection();
			
			conn.setAutoCommit(false); 
			
			st = conn.createStatement();
			
//			String query1 = String.format("delete from HM_MEMBER_ITEM where id = '%s'", inId);
//			st.executeUpdate(query1);
//			
//			String query2 = String.format("delete from HM_MEMBER_INGAME where id = '%s'", inId);
//			st.executeUpdate(query2);

			String query3 = String.format("delete from HM_MEMBER where id = '%s'", inId);
			st.executeUpdate(query3);

			conn.commit();

			select(); 
			
			deleteOk = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		return deleteOk;
	}
	
}
