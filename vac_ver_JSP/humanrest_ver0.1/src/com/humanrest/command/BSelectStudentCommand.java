package com.humanrest.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.humanrest.database.StudentDao;
import com.humanrest.database.StudentDto;

public class BSelectStudentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		StudentDao dao = new StudentDao();
		ArrayList<StudentDto> dtos = dao.studentSelectList();
		request.setAttribute("list", dtos);
		
	}

}
