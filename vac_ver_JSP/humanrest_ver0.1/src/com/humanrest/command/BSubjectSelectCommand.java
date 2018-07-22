package com.humanrest.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.humanrest.database.StudentDao;
import com.humanrest.database.StudentDto;
import com.humanrest.database.SubjectDao;
import com.humanrest.database.SubjectDto;

public class BSubjectSelectCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SubjectDao dao = new SubjectDao();
		ArrayList<SubjectDto> dtos = dao.subjectSelect();
		request.setAttribute("list", dtos);
		
	}

}
