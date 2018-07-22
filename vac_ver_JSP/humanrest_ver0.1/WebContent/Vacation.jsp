<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Vacation</title>
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
	<link rel="stylesheet" type="text/css" href="css/admin.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="left-menu">
					<a href="/MyInfo">
						<h4>OOO님,</h4>
					</a>
					<a href="index.html">
						logout
					</a>
					<hr>
					<div class="menu">
						<div class="m1">
							학생관리
							<ul class="m2">
								<li><a href="StudentAdd.html">학생등록</a></li>
								<li><a href="StudentSelect.do">학생조회</a></li>
							</ul>
						</div>
						<div class="m1">
							휴가 관리
							<ul class="m2">
								<li><a href="AdminPage.html">최근 신청 내역</a></li>
								<li><a href="vacation.do">휴가내역조회</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="right-view">
					<div class="search">
						<div class="btn">
							<img src="images/search.png"></img>
						</div>
						<input placeholder="search"></input>
					</div>
					<div class="board">
						<table>
							<tr>
									<th>열0<span><span></th>
									<th>열0</th>
									<th>열0</th>
									<th>열0</th>
									<th>열0</th>
									<th>열0</th>
									<th>열0</th>
							</tr>
							
							<c:forEach items="${list}" var="dto">
							<tr>
									<td>${dto.sub_name}</td>
									<td>${dto.stu_name}</td>
									<td>${dto.stu_number}</td>
									<td>${dto.stu_password}</td>
									<td>${dto.stu_birthday}</td>
									<td>${dto.stu_addr}</td>
									<td>${dto.stu_phone}</td>
									<td>${dto.available_rest}</td>
									<td>${dto.used_rest}</td>
							</tr>
							</c:forEach>
						
						</table>
						<hr>
						<div class="bottom">
								<span><a href="">1</a></span>
								<span>,<a href="">2</a></span>
								<span>,<a href="">3</a></span>
								<span>,<a href="">4</a></span>
								<span>,<a href="">5</a></span>
						</div>
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
	<script >
		$(".m1:nth-child(1) .m2").hide();
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
<!--===============================================================================================-->
	<script src="js/admin.js"></script>

</body>
</html>