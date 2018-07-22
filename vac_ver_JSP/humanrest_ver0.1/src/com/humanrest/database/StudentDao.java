package com.humanrest.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDao {
	
	SimpleDateFormat myDate = new SimpleDateFormat("yyyy년 MM월 dd일");

	public ArrayList<StudentDto> findMyId(String name, String phone) {
		ArrayList<StudentDto> dtos = new ArrayList<StudentDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

			connection = ds.getConnection();

			String query = String.format(
					"select stu_number, stu_password from HM_STUDENT where stu_name = '%s' and stu_phone = '%s'",
					name, phone);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String stu_number = resultSet.getString("stu_number");
				String stu_password = resultSet.getString("stu_password");

				StudentDto dto = new StudentDto(stu_number, stu_password);
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
	
	public ArrayList<StudentDto> myInfo(String id) {
		ArrayList<StudentDto> dtos = new ArrayList<StudentDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

			connection = ds.getConnection();

			String query = String.format(
					"select sub_name, stu_name, stu_number, stu_password, stu_birthday, stu_addr, stu_phone, available_rest, used_rest from HM_STUDENT where stu_number = '%s'",
					id);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String sub_name = resultSet.getString("sub_name");
				String stu_name = resultSet.getString("stu_name");
				String stu_number = resultSet.getString("stu_number");
				String stu_password = resultSet.getString("stu_password");
				Timestamp stu_birthday = resultSet.getTimestamp("stu_birthday");
				String stu_addr = resultSet.getString("stu_addr");
				String stu_phone = resultSet.getString("stu_phone");
				int available_rest = resultSet.getInt("available_rest");
				int used_rest = resultSet.getInt("used_rest");

				StudentDto dto = new StudentDto(sub_name, stu_name, stu_number, stu_password, stu_birthday, stu_addr,
						stu_phone, available_rest, used_rest);
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
	
	//sub_name, stu_name, stu_number, stu_password, d1, stu_addr, stu_phone, d2, d3
	
	public void StudentInsert(String sub_name, String stu_name, String stu_number, String stu_password, Date d1,
			String stu_addr, String stu_phone, int d2, int d3) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

			connection = ds.getConnection();
		
			String query = String.format(
					"insert into HM_STUDENT (sub_name, stu_name, stu_number, stu_password, stu_birthday, stu_addr, stu_phone, available_rest, used_rest) "
							+ "values ('%s', '%s', '%s',  '%s', '%s' ,'%s', '%s', %d, %d)",
					sub_name, stu_name, stu_number, stu_password, d1, stu_addr, stu_phone, d2, d3);
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
	
	public ArrayList<StudentDto> studentSelectList() {
		ArrayList<StudentDto> dtos = new ArrayList<StudentDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			connection = ds.getConnection();

			String query = "select * from hm_Student where NOT sub_name='관리자' order by STU_NUMBER";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String sub_name = resultSet.getString("sub_name");
				String stu_name = resultSet.getString("stu_name");
				String stu_number = resultSet.getString("stu_number");
				String stu_birthday = myDate.format(resultSet.getTimestamp("stu_birthday"));
				String stu_addr = resultSet.getString("stu_addr");
				String stu_phone = resultSet.getString("stu_phone");
				int available_rest = resultSet.getInt("available_rest");
				int used_rest = resultSet.getInt("used_rest");
				
				StudentDto dto = new StudentDto(sub_name, stu_name, stu_number, stu_birthday, stu_addr, stu_phone, available_rest, used_rest);
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

}
