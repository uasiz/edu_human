<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>

<%

	request.setCharacterEncoding("UTF-8");
	
    // String sub_name, String stu_number, String stu_name, int rest_num, Timestamp rest_request_date, 
    // Timestamp rest_date, String rest_reason, String stu_check
    
    String sub_name = request.getParameter("sub_name");
	String stu_number = request.getParameter("stu_number");
	String stu_name = request.getParameter("stu_name");
	String rest_num = request.getParameter("rest_num");
	String rest_request_date = request.getParameter("now_data");
	String rest_date = request.getParameter("date");
	String rest_reason = request.getParameter("reason");
	String stu_check = request.getParameter("check");
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="restRequest.do" method="post">
	<table>
	<tr>
		<td>과목명</td>
		<td><input type="text" name="sub_name" value="<%=sub_name%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>학번</td>
		<td><input type="text" name="stu_number" value="<%=stu_number%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>학생이름</td>
		<td><input type="text" name="stu_name" value="<%=stu_name%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>휴가 회차</td>
		<td><input type="text" name="rest_num" value="<%=rest_num%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>신청일</td>
		<td><input type="text" name="rest_request_date" value="<%=rest_request_date%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>휴가일</td>
		<td><input type="text" name="rest_date" value="<%=rest_date%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>신청 이유</td>
		<td><input type="text" name="rest_reason" value="<%=rest_reason%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>학생 서명 </td>
		<td><img src="<%=stu_check%>" width="100" height="30">
		<input type="hidden" name="stu_check" value="<%=stu_check%>" readonly="readonly"></td>
	</tr>
	<tr>
	<td colspan="2"><input type="submit" value="신청하기"><input type="button" value="취소하기" onclick="history.back()"></td>
	</tr>	
	</table>
</form>
</body>
</html>