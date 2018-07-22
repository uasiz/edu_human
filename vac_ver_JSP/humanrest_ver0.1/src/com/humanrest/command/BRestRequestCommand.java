package com.humanrest.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humanrest.database.RestDao;

public class BRestRequestCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String sub_name = request.getParameter("sub_name");
		String stu_number = request.getParameter("stu_number");
		String stu_name = request.getParameter("stu_name");
		int rest_num = Integer.parseInt(request.getParameter("rest_num"));
		Date rest_request_date = java.sql.Date.valueOf(request.getParameter("rest_request_date"));
		Date rest_date = java.sql.Date.valueOf(request.getParameter("rest_date"));
		String rest_reason = request.getParameter("rest_reason");
		String stu_check = request.getParameter("stu_check");
		
		RestDao dao = new RestDao();
		dao.requestRest(sub_name, stu_number, stu_name, rest_num, rest_request_date, rest_date, rest_reason, stu_check);
		
	}

}
