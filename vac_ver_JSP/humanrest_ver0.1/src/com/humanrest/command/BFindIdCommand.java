package com.humanrest.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humanrest.database.RestDao;
import com.humanrest.database.RestDto;
import com.humanrest.database.StudentDao;
import com.humanrest.database.StudentDto;

public class BFindIdCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("stuName");
		String phone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-" + request.getParameter("phone3");
		
		System.out.println(name + "//" + phone);
		
		StudentDao dao = new StudentDao();
		ArrayList<StudentDto> dtos = dao.findMyId(name, phone);
		request.setAttribute("list", dtos);
		
	}

}
