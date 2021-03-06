package com.humanrest.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humanrest.database.RestDao;

public class BManagerRestApprovalCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String restIndex = request.getParameter("restIndex");
		
		RestDao dao = new RestDao();
		
		dao.restApproval(Integer.parseInt(restIndex));
		
	}

}
