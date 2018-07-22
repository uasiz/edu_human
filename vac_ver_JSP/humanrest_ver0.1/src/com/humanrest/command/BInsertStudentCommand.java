package com.humanrest.command;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humanrest.database.RestDao;
import com.humanrest.database.RestDto;
import com.humanrest.database.StudentDao;
import com.humanrest.database.StudentDto;

public class BInsertStudentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String sub_name = request.getParameter("sub_name");
		String stu_name = request.getParameter("stu_name");
		String stu_number = request.getParameter("stu_number");
		String stu_password = request.getParameter("stu_password");
		
		String s1 = request.getParameter("stu_birthday");
		Date d1 = java.sql.Date.valueOf(s1);
		
		String stu_addr = request.getParameter("stu_addr");
		String stu_phone = request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
		
		int d2 = 0;
		int d3 = 0;
		
		StudentDao dao = new StudentDao();
		dao.StudentInsert(sub_name, stu_name, stu_number, stu_password, d1, stu_addr, stu_phone, d2, d3);
		
	}

}
