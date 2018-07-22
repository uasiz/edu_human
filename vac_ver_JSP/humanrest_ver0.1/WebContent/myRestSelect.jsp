<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.humanrest.database.StudentDto"%>
<%@ page import="com.humanrest.database.RestDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<%
	String id = (String) session.getAttribute("id");
	if (session.getAttribute("id") == null) {
		pageContext.forward("loginForm.jsp");
	}

	String name = (String) session.getAttribute("name");
	ArrayList<StudentDto> myRestList = (ArrayList<StudentDto>) request.getAttribute("myInfo");

	int myRestDay = myRestList.get(0).getAvailable_rest(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 휴가 조회</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/student.css">
<!--===============================================================================================-->

</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<jsp:include page="MenuStudent.jsp" flush="false"> 
					<jsp:param value="<%=name %>" name="name"/> 
				</jsp:include>
				<div class="right-view">
					<div class='head-line'>
						<span class='day'><%=myRestDay%></span> <span class='info'>일
							남았습니다..</span>
					</div>
					<hr>
					<div class='rest-logs'>
						<c:choose>
							<c:when test="${fn:length(myRest) == 0}">
								<h3>휴가 신청 내역이 없습니다.</h3>
							</c:when>
							<c:otherwise>
								<table>
									<tr>
										<th>신청 번호</th>
										<th>휴가 신청일</th>
										<th>휴가일</th>
										<th>휴가 회차</th>
										<th>휴가 사유</th>
										<th>학생 서명</th>
										<th>승인 여부</th>
										<th>모두 인쇄</th>
									</tr>
									<c:forEach items="${myRest}" var="dto">
										<tr>
											<td>${dto.rest_index}</td>
											<td>${dto.rest_request_date}</td>
											<td>${dto.rest_date}</td>
											<td>${dto.rest_num}회</td>
											<td>${dto.rest_reason}</td>
											<td><img src="${dto.stu_check}" width="100" height="30"></td>
											<td>${dto.verified}</td>
											<td>인쇄하기<td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>




	<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/tilt/tilt.jquery.min.js"></script>
	<script>
		$('.js-tilt').tilt({
			scale : 1.1
		})
	</script>
	<!--===============================================================================================-->
	<script src="js/student.js"></script>
	<!--===============================================================================================-->

</body>
</html>