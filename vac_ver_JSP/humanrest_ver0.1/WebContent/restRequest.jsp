<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.humanrest.database.StudentDto"%>
<%@ page import="java.util.ArrayList"%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
			$('.enter').val("등록하기");
			$('.enter').get(0).type = 'submit';
			}, 5000);
		$('.enter').val("잠시만 기다려주세요..");
	}
</script>

</head>
<body>
<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<jsp:include page="MenuStudent.jsp" flush="false"> 
					<jsp:param value="<%=name %>" name="name"/> 
				</jsp:include>
				<div class="right-view">
					<form form action="requestRestProcess.jsp" method="post" name="userinput">
						<h4>휴가 신청</h4>
						<hr>
						<table class='report'>
								<tr>
									<td>휴가 날짜 </td>
									<td><input type="date" name="date"></td>
								</tr>
								<tr>
									<td>휴가 사유 </td>
									<td><input type="text" name="reason"></td>
								</tr>
								<tr>
									<td>학생 서명</td>
									<td>
									<img src = "images/sign.png" id="mySign" onclick="showPopup();">
									</td>
								</tr>
									<tr>
									<td><input type="hidden" name="check" value="" id="mySignInput">
									<input type="hidden" name="sub_name" value="<%=sub_name%>">
										<input type="hidden" name="stu_number" value="<%=stu_number%>">
										<input type="hidden" name="stu_name" value="<%=stu_name%>">
										<input type="hidden" name="rest_num" value="<%=rest_num%>">
										<input type="hidden" name="now_data" id="myNowData" value=""></td>
								</tr>
								<tr>
									<td colspan="2"><input type="Button" class='enter' value="등록하기 전에 서명을 완료해주세요."></td>
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