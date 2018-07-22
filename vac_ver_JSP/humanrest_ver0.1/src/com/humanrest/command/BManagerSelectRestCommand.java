package com.humanrest.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humanrest.database.RestDao;
import com.humanrest.database.RestDto;

public class BManagerSelectRestCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("되나1?");
		RestDao dao = new RestDao();
		ArrayList<RestDto> dtos = dao.managerSelectRequest();
		request.setAttribute("list", dtos);
		
	}

}
