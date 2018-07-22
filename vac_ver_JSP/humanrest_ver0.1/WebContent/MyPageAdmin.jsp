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
	<title>AdminInfo</title> 
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
						<form action="" method="post">
								<h4>관리자 정보 보기</h4>
								<hr>
								<table>
										<tr>
											<td>이름 </td>
											<td><input type='text' name='stu_name' placeholder="성+이름"></td>
										</tr>
										<tr>
											<td>비밀번호 </td>
											<td><input type='password' name='stu_password' placeholder="입력해주세요.."></td>
										</tr>
										<tr>
											<td colspan="2"><input type="submit" value="수정하기"></td>
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
	<script src="js/admin.js"></script>
<!--===============================================================================================-->

</body>
</html>