<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<% 
	String id = (String) session.getAttribute("id");
	if(session.getAttribute("id") == null) {
		pageContext.forward("loginForm.jsp");
	}
	String name = (String) session.getAttribute("name"); 
%>
<!DOCTYPE html>
<head>
	<title>학생 정보 수정</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
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
						<form action="" method="post">
								<h4>나의 정보</h4>
								<hr>
								<table>
										<tr>
											<td>과목명 </td>
											<td><input type='text' name='sub_name' placeholder="입력 해주세요."></td>
										</tr>
										<tr>
											<td>학생 이름 </td>
											<td><input type='text' name='stu_name' placeholder="성+이름"></td>
										</tr>
										<tr>
											<td>학생 번호 </td>
											<td><input type='text' name='stu_number' placeholder="학번"></td>
										</tr>
										<tr>
											<td>학생 비밀번호 </td>
											<td><input type='text' name='stu_password' value='1111' placeholder="입력 해주세요."></td>
										</tr>
										<tr>
											<td>학생 생년월일 </td>
											<td><input type='text' name='stu_birthday' placeholder="YYYY-MM-DD"></td>
										</tr>
										<tr>
											<td>학생 주소 </td>
											<td><input type='text' name='stu_addr' placeholder="주거지역 주소"></td>
										</tr>
										<tr>
											<td>학생 전화번호 </td>
											<td><input type='text' name='stu_phone' placeholder="Tel."></td>
										</tr>
										<tr>
											<td>학생 사용가능 휴가일 수 </td>
											<td><input type='text' name='available_rest' value="10" placeholder="입력 해주세요."></td>
										</tr>
										<tr>
											<td>학생 사용한 휴가일 수 </td>
											<td><input type='text' name='available_rest' value="0" placeholder="입력 해주세요."></td>
										</tr>
										<tr>
											<td colspan="2"><input class='enter' type="submit" value="수정하기"></td>
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
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
<!--===============================================================================================-->
	<script src="js/student.js"></script>
<!--===============================================================================================-->

</body>
</html>