<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String id = (String) session.getAttribute("id");
	if (session.getAttribute("id") == null) {
		pageContext.forward("loginForm.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

	function mySubmit(index) {
		if (index == 1) {
			document.managerForm.action = 'restApproval.do';
		}
		if (index == 2) {
			document.managerForm.action = 'restRestore.do';
		}
		document.managerForm.submit();
	}
	
</script>
</head>
<body>
	휴가 신청 내역
	<br>
	<form name="managerForm" method="post">
		<table border='1'>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>신청 번호</td>
					<td>${dto.rest_index} 
					
					<c:if test="${dto.verified eq '승인 심사 중' }">
  					<input type="checkbox" name="restIndex" value="${dto.rest_index}">
					</c:if>
					
					</td>
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
					<td>${dto.rest_num}회</td>
				</tr>
				<tr>
					<td>휴가 이유</td>
					<td>${dto.rest_reason}</td>
				</tr>
				<tr>
					<td>학생 서명</td>
					<td><img src = "${dto.stu_check}"></td>
				</tr>
				<tr>
					<td>승인 여부</td>
					<td>${dto.verified}</td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2"><input type="button" value="승인하기" onClick="mySubmit(1)">
				<input type="button" value="반려하기" onClick="mySubmit(2)"></td>
			</tr>
		</table>
	</form>
	<br>
	<input type="button" value="메인화면으로 돌아가기"
		onclick="location.href='loginProcess.jsp'">

</body>
</html>