<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>최근 휴가 신청 내역</title>
<!--===============================================================================================-->	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
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

<script>
	function mySubmit(index) {
		if (index == 1) {
	
			var result = confirm("휴가를 승인하시겠습니까?");
	
			if (result == true) {
				document.managerForm.action = 'restApproval.do';
			}
		}
		if (index == 2) {
			var result = confirm("휴가를 반려하시겠습니까?");
	
			if (result == true) {
				document.managerForm.action = 'restRestore.do';
			}
	
		}
		document.managerForm.submit();
	}
</script>
</head>
<body>
<form name='managerForm' method='post' action='#'>
<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">			
				<jsp:include page="MenuAdmin.jsp" flush="false"> 
					<jsp:param value="<%=name %>" name="name"/> 
				</jsp:include>
				<div class="right-view">
					
				</div>
				
			</div>
		</div>
	</div>
	</form>

	

	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/tilt/tilt.jquery.min.js"></script>
	<script >
		$(".m1:nth-child(1) .m2").hide();
		$('.js-tilt').tilt({
			scale: 1.1
		});
		
		<c:choose>
		    <c:when test="${fn:length(list) == 0}">
		    
		    var noRest = "심사중인 휴가가 존재하지 않습니다";
		    $(".right-view").append("<div class='vacation-null'>" + noRest + "</div>");
		    	
	        </c:when>    
	        <c:otherwise>
			<c:forEach items="${list}" var="dto">

				var consentHtml = "<span class='consent'>"
						+ "<input type='hidden' name='restIndex' value='${dto.rest_index}'>"
						+ "<input type='image' src='images/consent.png' style='width:30px;height:30px;' onClick='mySubmit(1)'>"
						+ "</span>";
				var cancelHtml = "<span class='cancle'>"
						+ "<input type='hidden' name='restIndex' value='${dto.rest_index}'>"
						+ "<input type='image' src='images/cancle.png' style='width:30px;height:30px' onClick='mySubmit(2)'>"
						+ "</span>";
				var infoHtml = "<div class='info'>"
						+ "<hr>"
						+ "<table>"
						+ "<tr><td>신청 번호</td><td>수강 과목</td><td>학생 번호</td><td>학생 이름</td><td>휴가 신청일</td><td>휴가일</td><td>휴가 회차</td><td>휴가 이유</td><td>학생 서명</td></tr>"
						+ "<tr><td>${dto.rest_index}</td><td>${dto.sub_name}</td><td>${dto.stu_number}</td><td>${dto.stu_name}</td><td>${dto.rest_request_date}</td>"
						+ "<td>${dto.rest_date}</td><td>${dto.rest_num}회</td><td>${dto.rest_reason}</td><td><img src = '${dto.stu_check}' width='100' height='30'></td></tr>"
						+ "</table>" + "</div>";
				$(".right-view")
						.append(		
								"<div class='vacation'>${dto.stu_name}님이 휴가를 신청하였습니다."
								+ cancelHtml + consentHtml + infoHtml
								+ "</div>");
				</c:forEach>
			</c:otherwise> 
		</c:choose>
	</script>
<!--===============================================================================================-->
	<script src="js/admin.js"></script>

</body>
</html>