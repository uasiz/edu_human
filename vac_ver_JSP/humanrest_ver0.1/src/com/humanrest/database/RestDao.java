package com.humanrest.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RestDao {

	DataSource dataSource;

	public RestDao() {

		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public void requestRest(String sub_name, String stu_number, String stu_name, int rest_num, Date rest_request_date,
			Date rest_date, String rest_reason, String stu_check) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();
			String query = String.format(
					"insert into HM_REST_TABLE (rest_index, sub_name, stu_number, stu_name, rest_num, rest_request_date, rest_date, rest_reason, stu_check) "
							+ "values (HM_REST_SEQ.nextval, '%s', '%s', '%s', %d, '%s', '%s', '%s', '%s')",
					sub_name, stu_number, stu_name, rest_num, rest_request_date, rest_date, rest_reason, stu_check);
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

	public ArrayList<RestDto> myRestRequest(String id) {
		ArrayList<RestDto> dtos = new ArrayList<RestDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

			connection = ds.getConnection();

			String query = String.format(
					"select rest_index, sub_name, stu_number, stu_name, rest_request_date, rest_date, rest_num, rest_reason, stu_check, CASE WHEN verified is null THEN 'ing' WHEN verified = '0' THEN 'out' WHEN verified = '1' THEN 'ok' ELSE 'error' END AS verified from HM_REST_TABLE where stu_number = '%s' order by rest_index",
					id);
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				int rest_index = resultSet.getInt("rest_index");
				String sub_name = resultSet.getString("sub_name");
				String stu_number = resultSet.getString("stu_number");
				String stu_name = resultSet.getString("stu_name");
				Date rest_request_date = resultSet.getDate("rest_request_date");
				Date rest_date = resultSet.getDate("rest_date");
				int rest_num = resultSet.getInt("rest_num");
				String rest_reason = resultSet.getString("rest_reason");
				String verified = resultSet.getString("verified");
				String stu_check = resultSet.getString("stu_check");
				
				RestDto dto = new RestDto(rest_index, sub_name, stu_number, stu_name, rest_request_date, rest_date, rest_num, rest_reason, verified, stu_check);
				dtos.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	public ArrayList<RestDto> myRest(String id) {
		ArrayList<RestDto> dtos = new ArrayList<RestDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

			connection = ds.getConnection();

			String query = String.format(
					"select rest_index, sub_name, stu_number, stu_name, rest_request_date, rest_date, rest_num, rest_reason, stu_check, CASE WHEN verified is null THEN 'ing' WHEN verified = '0' THEN 'out' WHEN verified = '1' THEN 'ok' ELSE 'error' END AS verified from HM_REST_TABLE where stu_number = '%s' and verified = 1 order by rest_index",
					id);
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				int rest_index = resultSet.getInt("rest_index");
				String sub_name = resultSet.getString("sub_name");
				String stu_number = resultSet.getString("stu_number");
				String stu_name = resultSet.getString("stu_name");
				Date rest_request_date = resultSet.getDate("rest_request_date");
				Date rest_date = resultSet.getDate("rest_date");
				int rest_num = resultSet.getInt("rest_num");
				String rest_reason = resultSet.getString("rest_reason");
				String verified = resultSet.getString("verified");
				String stu_check = resultSet.getString("stu_check");
				
				RestDto dto = new RestDto(rest_index, sub_name, stu_number, stu_name, rest_request_date, rest_date, rest_num, rest_reason, verified, stu_check);
				dtos.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	public ArrayList<RestDto> managerSelectRequest() {
		ArrayList<RestDto> dtos = new ArrayList<RestDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

			connection = ds.getConnection();

			String query = "select rest_index, sub_name, stu_number, stu_name, rest_request_date, rest_date, rest_num, rest_reason, stu_check, "
					+ "CASE WHEN verified is null THEN 'ing' WHEN verified = '0' THEN 'out' "
					+ "WHEN verified = '1' THEN 'ok' ELSE 'error' END AS verified from HM_REST_TABLE order by rest_index";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				int rest_index = resultSet.getInt("rest_index");
				String sub_name = resultSet.getString("sub_name");
				String stu_number = resultSet.getString("stu_number");
				String stu_name = resultSet.getString("stu_name");
				Date rest_request_date = resultSet.getDate("rest_request_date");
				Date rest_date = resultSet.getDate("rest_date");
				int rest_num = resultSet.getInt("rest_num");
				String rest_reason = resultSet.getString("rest_reason");
				String verified = resultSet.getString("verified");
				//인코딩 문제체크
				//String verified_KS = new String(verified.getBytes("8859_1"), "KSC5601");
				String stu_check = resultSet.getString("stu_check");
				
				//String query = "insert into createtest values('"+new String("테스팅2".getBytes("8859_1"), "euc-kr")+"', '017-111-2222')"; 
			
				RestDto dto = new RestDto(rest_index, sub_name, stu_number, stu_name, rest_request_date, rest_date, rest_num, rest_reason, verified, stu_check);
				dtos.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	public ArrayList<RestDto> managerSelectWaitRequest() {
		ArrayList<RestDto> dtos = new ArrayList<RestDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

			connection = ds.getConnection();

			String query = "select rest_index, sub_name, stu_number, stu_name, rest_request_date, rest_date, rest_num, rest_reason, stu_check, CASE WHEN verified is null THEN 'ing' WHEN verified = '0' THEN 'out' WHEN verified = '1' THEN 'ok' ELSE 'error' END AS verified from HM_REST_TABLE where verified is null order by rest_index";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				int rest_index = resultSet.getInt("rest_index");
				String sub_name = resultSet.getString("sub_name");
				String stu_number = resultSet.getString("stu_number");
				String stu_name = resultSet.getString("stu_name");
				Date rest_request_date = resultSet.getDate("rest_request_date");
				Date rest_date = resultSet.getDate("rest_date");
				int rest_num = resultSet.getInt("rest_num");
				String rest_reason = resultSet.getString("rest_reason");
				String verified = resultSet.getString("verified");
				String stu_check = resultSet.getString("stu_check");
				
				//체크포인트
				System.out.println(verified);
				
				RestDto dto = new RestDto(rest_index, sub_name, stu_number, stu_name, rest_request_date, rest_date, rest_num, rest_reason, verified, stu_check);
				dtos.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
		
	}

	public ArrayList<RestDto> managerSelectCompleteRequest() {
		ArrayList<RestDto> dtos = new ArrayList<RestDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

			connection = ds.getConnection();

			String query = "select rest_index, sub_name, stu_number, stu_name, rest_request_date, rest_date, rest_num, rest_reason, stu_check, CASE WHEN verified is null THEN 'ing' WHEN verified = '0' THEN 'out' WHEN verified = '1' THEN 'ok' ELSE 'error' END AS verified from HM_REST_TABLE where verified = '0' or verified = '1' order by rest_index";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				int rest_index = resultSet.getInt("rest_index");
				String sub_name = resultSet.getString("sub_name");
				String stu_number = resultSet.getString("stu_number");
				String stu_name = resultSet.getString("stu_name");
				Date rest_request_date = resultSet.getDate("rest_request_date");
				Date rest_date = resultSet.getDate("rest_date");
				int rest_num = resultSet.getInt("rest_num");
				String rest_reason = resultSet.getString("rest_reason");
				String verified = resultSet.getString("verified");
				String stu_check = resultSet.getString("stu_check");
				
				RestDto dto = new RestDto(rest_index, sub_name, stu_number, stu_name, rest_request_date, rest_date, rest_num, rest_reason, verified, stu_check);
				dtos.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	public void restApproval(int index) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			
			connection = dataSource.getConnection();
			String query = String.format("update HM_REST_TABLE set verified = '1' "
					+ "where rest_index = %d", index);
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}

	public void restRestore(int index) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			
			connection = dataSource.getConnection();
			String query = String.format("update HM_REST_TABLE set verified = '0' "
					+ "where rest_index = %d", index);
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
		
}
