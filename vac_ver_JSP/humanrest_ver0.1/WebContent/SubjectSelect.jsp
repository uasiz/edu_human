<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<%
String name = (String) session.getAttribute("name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과목 조회</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<link rel="stylesheet" type="text/css" href="css/admin.css">
<!--===============================================================================================-->

</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">		
				<jsp:include page="MenuAdmin.jsp" flush="false"> 
					<jsp:param value="<%=name %>" name="name"/> 
				</jsp:include>
				<div class="right-view">
					<div class='title'>
						<img src="images/students.png"></img>
					</div>
					<input placeholder="search" class="search"></input>
					<div class="board">
						<table>
							<tr>
								<th>과목 이름</th>
								<th>개강 날짜</th>
								<th>종강 날짜</th>
							</tr>
							<c:forEach items="${list}" var="dto">
								<tr>
									<td>${dto.sub_name}</td>
									<td>${dto.sub_start}</td>
									<td>${dto.sub_end}</td>
								</tr>
							</c:forEach>
						</table>
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
		$(".m1:nth-child(2) .m2").hide();
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
		<!--===============================================================================================-->
		<script src="js/admin.js"></script>

</body>
</html>