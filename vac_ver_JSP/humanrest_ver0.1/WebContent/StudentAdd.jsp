<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%

    request.setCharacterEncoding("UTF-8");
 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String name = (String) session.getAttribute("name");
%>
<html>
<head>
<title>학생 등록</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
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
					<form action="insertStudent.do" method="post">
						<h4>학생 등록</h4>
						<hr>

						<table>
							<tr>
								<td>수강 과목</td>
								<td>
								<select name="sub_name">
									<c:forEach items="${list}" var="dto">
									  <option value="${dto.subName}">${dto.subName}</option>
									</c:forEach>
								</select> 
								</td>
							</tr>
							<tr>
								<td>학생 이름</td>
								<td><input type='text' name='stu_name' placeholder="성+이름"></td>
							</tr>
							<tr>
								<td>학생 번호</td>
								<td><input type='text' name='stu_number' placeholder="학번"></td>
							</tr>
							<tr>
								<td>학생 비밀번호</td>
								<td><input type='text' name='stu_password' value='1111' readOnly='readOnly'
									placeholder="입력 해주세요."></td>
							</tr>
							<tr>
								<td>학생 생년월일</td>
								<td><input type="date" name="stu_birthday">
								</td>
							</tr>
							<tr>
								<td>학생 주소</td>
								<td><input type='text' name='stu_addr'
									placeholder="주거지역 주소"></td>
							</tr>
							<tr>
								<td>학생 전화번호</td>
								<td><input type="text" name="phone1" maxlength="3" size="2"> - <input type="text" name="phone2" maxlength="4" size="3"> - <input type="text" name="phone3" maxlength="4" size="3"></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="등록하기"></td>
							</tr>
						</table>

					</form>
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