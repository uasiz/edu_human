package com.humanrest.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humanrest.database.RestDao;
import com.humanrest.database.RestDto;

public class BManagerSelectRestCompleteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		RestDao dao = new RestDao();
		ArrayList<RestDto> dtos = dao.managerSelectCompleteRequest();
		request.setAttribute("list", dtos);
		
	}

}
