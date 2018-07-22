package com.humanrest.frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.humanrest.command.BCommand;
import com.humanrest.command.BFindIdCommand;
import com.humanrest.command.BInsertStudentCommand;
import com.humanrest.command.BListCommand;
import com.humanrest.command.BManagerRestApprovalCommand;
import com.humanrest.command.BManagerRestRestoreCommand;
import com.humanrest.command.BManagerSelectRestCommand;
import com.humanrest.command.BManagerSelectRestCompleteCommand;
import com.humanrest.command.BManagerSelectRestWaitCommand;
import com.humanrest.command.BRestRequestCommand;
import com.humanrest.command.BSelectRestCommand;
import com.humanrest.command.BSelectRestRequestCommand;
import com.humanrest.command.BSelectStudentCommand;
import com.humanrest.command.BStudentAddBeforeCommand;
import com.humanrest.command.BSubjectSelectCommand;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ActionDo 실행하였습니다.");
		System.out.println("ActionDo 실행하였습니다.");
		System.out.println("ActionDo 실행하였습니다.");
		
		response.setContentType("text/plain;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		if(com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
		} else if(com.equals("/stuLogin.do")) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			command = new BListCommand(id);
			command.execute(request, response);
			viewPage = "restRequest.jsp";
		} else if(com.equals("/restRequest.do")) {
			command = new BRestRequestCommand();
			command.execute(request, response);
			viewPage = "selectRestProcess.do";
		} else if(com.equals("/selectRestProcess.do")) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			command = new BSelectRestRequestCommand(id);
			command.execute(request, response);
			viewPage = "myRestSelect.jsp";
		} else if(com.equals("/selectRest.do")) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			command = new BSelectRestCommand(id);
			command.execute(request, response);
			viewPage = "myRest.jsp";
		} else if(com.equals("/managerRestSelect.do")) {
			System.out.println("되나0?");
			command = new BManagerSelectRestCommand();
			command.execute(request, response);
			viewPage = "managerRestSelect.jsp";	
		} else if(com.equals("/restApproval.do")) {
			command = new BManagerRestApprovalCommand();
			command.execute(request, response);
			viewPage = "manager.do";
		} else if(com.equals("/restRestore.do")) {
			command = new BManagerRestRestoreCommand();
			command.execute(request, response);
			viewPage = "manager.do";
		} else if(com.equals("/managerRestCompleteSelect.do")) {
			command = new BManagerSelectRestCompleteCommand();
			command.execute(request, response);
			viewPage = "managerRestRequest.jsp";
		} else if(com.equals("/manager.do")) {
			command = new BManagerSelectRestWaitCommand();
			command.execute(request, response);
			viewPage = "managerMain.jsp";
		} else if(com.equals("/insertStudent.do")) {
			command= new BInsertStudentCommand();
			command.execute(request, response);
			viewPage = "StudentSelect.do";
		} else if(com.equals("/StudentSelect.do")) {
			command = new BSelectStudentCommand();
			command.execute(request, response);
			viewPage = "StudentSelect.jsp";
		} else if(com.equals("/findMyId.do")) {
			command = new BFindIdCommand();
			command.execute(request, response);
			viewPage = "findIdSuccess.jsp";
		} else if(com.equals("/StudentAdd.do")) {
			command = new BStudentAddBeforeCommand();
			command.execute(request, response);
			viewPage = "StudentAdd.jsp";
		} else if(com.equals("/SubjectSelect.do")) {
			command = new BSubjectSelectCommand();
			command.execute(request, response);
			viewPage = "SubjectSelect.jsp";
		} else if(com.equals("/SubjectAdd.do")) {
			//command = new 
			//command.execute(request, response);
			viewPage = "SubjectAdd.jsp";
		}  
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

	
	// <Resource auth="Container" driverClassName="oracle.jdbc.driver.OracleDriver" factory="org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory" maxActive="50" maxIdle="1000" name="jdbc/OracleDB" password="1111" type="javax.sql.DataSource" url="jdbc:oracle:thin:@localhost:1521:orcl" username="human"/>
	
	
}
