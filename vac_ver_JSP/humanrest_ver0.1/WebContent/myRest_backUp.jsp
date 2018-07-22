<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String id = (String) session.getAttribute("id");
	if(session.getAttribute("id") == null) {
		pageContext.forward("loginForm.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
내 휴가  승인 내역<br>
<table border='1'>
<c:forEach items="${list}" var="dto">
	<tr>
		<td>신청 번호</td>
		<td>${dto.rest_index}</td>
	</tr>
	<tr>
		<td>수강 과목</td>
		<td>${dto.sub_name}</td>
	</tr>
	<tr>
		<td>학생 번호</td>
		<td>${dto.stu_number}</td>
	</tr>
	<tr>
		<td>학생 이름</td>
		<td>${dto.stu_name}</td>
	</tr>
	<tr>
		<td>휴가 신청일</td>
		<td>${dto.rest_request_date}</td>
	</tr>
	<tr>
		<td>휴가일</td>
		<td>${dto.rest_date}</td>
	</tr>
	<tr>
		<td>휴가 회차</td>
		<td>${dto.rest_num} 회</td>
	</tr>
	<tr>
		<td>휴가 이유</td>
		<td>${dto.rest_reason}</td>
	</tr>
	<tr>
		<td>학생 서명</td>
		<td><img src = "${dto.stu_check}" width='100' height='30'></td>
	</tr>
	<tr>
		<td>승인 여부</td>
		<td>${dto.verified}</td>
	</tr>
	<tr>
		<td colspan="2"></td>
	</tr>
</c:forEach>
</table>
<br>
<input type="button" value="메인화면으로 돌아가기" onclick="location.href='loginProcess.jsp'">

</body>
</html>