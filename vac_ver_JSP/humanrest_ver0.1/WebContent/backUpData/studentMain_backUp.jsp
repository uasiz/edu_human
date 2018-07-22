<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.humanrest.database.StudentDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String id = (String) session.getAttribute("id");
	if (session.getAttribute("id") == null) {
		pageContext.forward("loginForm.jsp");
	}

	ArrayList<StudentDto> dtos = (ArrayList<StudentDto>) request.getAttribute("list");

	String sub_name = dtos.get(0).getSub_name();
	String stu_number = dtos.get(0).getStu_number();
	String stu_name = dtos.get(0).getStu_name();
	int rest_num = dtos.get(0).getUsed_rest() + 1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BananCola HumanMind 자유게시판</title>
<script language="javascript">
	window.onload = function(){ 
		var date = new Date(); 
		var year = date.getFullYear(); 
		var month = new String(date.getMonth()+1); 
		var day = new String(date.getDate()); 
		
		// 한자리수일 경우 0을 채워준다. 
		if(month.length == 1){ 
		  month = "0" + month; 
		} 
		if(day.length == 1){ 
		  day = "0" + day; 
		} 
		
		document.getElementById("myNowData").value = year + "-" + month + "-" + day;
	
	}
	function showPopup() {
		window.open("mySignPage.jsp", "서명입력창",
				"width=300, height=130, left=500, top=50 resizable=no, scrollbars=no, status=no");
	}
	function changeImg(s) {
		document.getElementById("mySignInput").value = s;
		setTimeout(function() {
			mySign.src = s;
			}, 5000);
		
	}
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		$("#logoutBtn").click(function() {

			var result = confirm('정말로 로그아웃 하시겠습니까?');

			if (result == true) {
				window.location.href = "loginForm.jsp";
			}

		});

	})
</script>
</head>
<body>
	학생 페이지입니다.
	<br>
	<br> 내 정보
	<br>
	<table>
		<c:forEach items="${list}" var="dto">
			<tr>
				<td>수강과목</td>
				<td>${dto.sub_name}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${dto.stu_name}</td>
			</tr>
			<tr>
				<td>생일</td>
				<td>${dto.stu_birthday}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${dto.stu_addr}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>${dto.stu_phone}</td>
			</tr>
			<tr>
				<td>사용 가능 휴가</td>
				<td>${dto.available_rest}일</td>
			</tr>
			<tr>
				<td>사용 된 휴가</td>
				<td>${dto.used_rest}일</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>

	<!-- String sub_name, String stu_number, String stu_name, int rest_num -->

	휴가 신청하기
	<form action="requestRestProcess.jsp" method="post" name="userinput">
		<table>
			<tr>
				<td>휴가 날짜</td>
				<td><input type="date" name="date"></td>
			</tr>
			<tr>
				<td>휴가 사유</td>
				<td><input type="text" name="reason"></td>
			</tr>
			<tr>
				<td>학생 확인</td>
				<td id="imageCell">
				<img src = "" id="mySign" width='100' height='30'>
				<!-- <input type="text" name="check" readOnly="readOnly" id="mySign"> --> 
				<input type="button" value="서명하기" onclick="showPopup();"></td>
			</tr>
			<tr>
				<td><input type="hidden" name="check" value="" id="mySignInput">
				<input type="hidden" name="sub_name" value="<%=sub_name%>">
					<input type="hidden" name="stu_number" value="<%=stu_number%>">
					<input type="hidden" name="stu_name" value="<%=stu_name%>">
					<input type="hidden" name="rest_num" value="<%=rest_num%>">
					<input type="hidden" name="now_data" id="myNowData" value=""></td>
			</tr>
			<!--   String sub_name = request.getParameter("sub_name");
	String stu_number = request.getParameter("stu_number");
	String stu_name = request.getParameter("stu_name");
	String rest_num = request.getParameter("rest_num");
	String rest_request_date = "2018-04-05";
	String rest_date = request.getParameter("date");
	String rest_reason = request.getParameter("reason");
	String stu_check = request.getParameter("check"); -->
			<tr>
				<td colspan="2"><input type="submit" value="휴가 신청 하기"></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<input type="button" value="휴가 신청 내역 조회"
		onclick="location.href='selectRestProcess.do'">
	<br>
	<br>
	<br>
	<input type="button" value="승인된 휴가 조회"
		onclick="location.href='selectRest.do'">
	<br>
	<br>
	<br>
	<button type="button" id="logoutBtn">로그아웃</button>
</body>
</html>