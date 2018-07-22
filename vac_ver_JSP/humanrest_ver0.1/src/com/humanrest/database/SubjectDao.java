package com.humanrest.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SubjectDao {
	
	SimpleDateFormat myDate = new SimpleDateFormat("yyyy년 MM월 dd일");
	
	public ArrayList<SubjectDto> subList() {
		ArrayList<SubjectDto> dtos = new ArrayList<SubjectDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

			connection = ds.getConnection();

			String query = "select sub_name from HM_SUBJECT WHERE NOT sub_name='관리자'";
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String sub_name = resultSet.getString("sub_name");
				System.out.println(sub_name);
				SubjectDto dto = new SubjectDto(sub_name);
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
	
	public ArrayList<SubjectDto> subjectSelect() {
		ArrayList<SubjectDto> dtos = new ArrayList<SubjectDto>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			connection = ds.getConnection();

			String query = "select * from hm_subject WHERE NOT sub_name='관리자' order by sub_start";
			
			System.out.println(query);
			
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String sub_name = resultSet.getString("sub_name");
				String sub_start = myDate.format(resultSet.getTimestamp("sub_start"));
				String sub_end = myDate.format(resultSet.getTimestamp("sub_end"));
				
				SubjectDto dto = new SubjectDto(sub_name, sub_start, sub_end);
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
