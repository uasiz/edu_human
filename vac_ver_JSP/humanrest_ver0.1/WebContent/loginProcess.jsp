<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.*"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<%
	String id = "";

	if(session.getAttribute("id") != null) {
		out.println("<script>");
		out.println("location.href='stuLogin.do'");
		out.println("</script>");
	} else {
		id = request.getParameter("id");
	}
	
	String pass = request.getParameter("pass");
	String name = "";

	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;

	try {

		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		conn = ds.getConnection();

		String query = String.format("Select * from HM_STUDENT where stu_number = '%s'", id);
		st = conn.createStatement();
		rs = st.executeQuery(query);

		if (rs.next()) {
			name = rs.getString("stu_name");
			if (pass.equals(rs.getString("stu_password"))) {
				session.setAttribute("id", id);
				session.setAttribute("name", name);
				out.println("<script>");
				out.println("alert('" + name + "님 어서오세요!')");
				if(id.equals("human")){
					out.println("location.href='manager.do'");
					out.println("</script>");
				} else {
					out.println("location.href='selectRestProcess.do'");
					out.println("</script>");	
				}
			}
		}

		out.println("<script>");
		out.println("alert('로그인에 실패했습니다!')");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");

	} catch (Exception e) {
		e.printStackTrace();
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>