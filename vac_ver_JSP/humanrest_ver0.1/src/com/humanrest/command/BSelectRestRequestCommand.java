package com.humanrest.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humanrest.database.RestDao;
import com.humanrest.database.RestDto;
import com.humanrest.database.StudentDao;
import com.humanrest.database.StudentDto;

public class BSelectRestRequestCommand implements BCommand {

	private String id;
	
	public BSelectRestRequestCommand(String id) {
		this.id = id;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		RestDao dao = new RestDao();
		ArrayList<RestDto> dtos = dao.myRestRequest(id);
		request.setAttribute("myRest", dtos);
		
		StudentDao dao2 = new StudentDao();
		ArrayList<StudentDto> dtos2 = dao2.myInfo(id);
		request.setAttribute("myInfo", dtos2);
		
	}

}
