package com.humanrest.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humanrest.database.RestDao;
import com.humanrest.database.RestDto;

public class BSelectRestCommand implements BCommand {

	private String id;

	public BSelectRestCommand(String id) {
		this.id = id;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		RestDao dao = new RestDao();
		ArrayList<RestDto> dtos = dao.myRest(id);
		request.setAttribute("list", dtos);

	}

}
