<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<%

	String name = (String) session.getAttribute("name");
	String id = (String) session.getAttribute("id");
	if (session.getAttribute("id") == null) {
		pageContext.forward("loginForm.jsp");
	}
	
	request.setCharacterEncoding("UTF-8");
	
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>휴가 신청</title>
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
					<form action="restRequest.do" method="post">
						<h4>휴가 신청 내역 확인</h4>
						<hr>
						<table>
								<tr>
									<td>휴가 날짜 </td>
									<td><input type="text" name="rest_date" value="<%=rest_date%>" readonly="readonly"></td>
								</tr>
								<tr>
									<td>휴가 사유 </td>
									<td><input type="text" name="rest_reason" value="<%=rest_reason%>" readonly="readonly"></td>
								</tr>
								<tr>
									<td>학생 서명</td>
									<td>
									<img src="<%=stu_check%>" width="100" height="30">
									<input type="hidden" name="stu_check" value="<%=stu_check%>" readonly="readonly"></td>
								</tr>
								<tr>
									<td>
									<input type="hidden" name="check" value="" id="mySignInput">
									<input type="hidden" name="sub_name" value="<%=sub_name%>">
										<input type="hidden" name="stu_number" value="<%=stu_number%>">
										<input type="hidden" name="stu_name" value="<%=stu_name%>">
										<input type="hidden" name="rest_num" value="<%=rest_num%>">
										<input type="hidden" name="now_data" id="myNowData" value="">
										<input type="hidden" name="rest_request_date" value="<%=rest_request_date%>">
										<input type="hidden" name="rest_date" value="<%=rest_date%>">
										
										</td>
								</tr>
								<tr>
									<td><input type="submit" class='enter' value="등록하기"></td>
									<td><input type="button" class='enter' value="취소하기" onclick="location.href='stuLogin.do'"></td>
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
		$(".m1:nth-child(2) .m2").hide();
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
<!--===============================================================================================-->
	<script src="js/student.js"></script>

</body>
</html>