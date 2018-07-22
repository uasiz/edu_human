package com.humanrest.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humanrest.database.StudentDao;
import com.humanrest.database.StudentDto;

public class BListCommand implements BCommand {
	
	private String id;
	
	public BListCommand(String id) {
		this.id = id;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		StudentDao dao = new StudentDao();
		ArrayList<StudentDto> dtos = dao.myInfo(id);
		request.setAttribute("list", dtos);
		
	}

}
