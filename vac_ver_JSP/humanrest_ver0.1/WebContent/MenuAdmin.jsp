<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<%
	String name = request.getParameter("name");
%>
<script>
function logout(){
	var result = confirm('정말로 로그아웃 하시겠습니까?');
	
	if (result == true){
		window.location.href = "loginForm.jsp";
	}
}
</script>
				<div class="left-menu">
					<div class='profile'>
						<a href="MyPageAdmin.jsp">
							<h4><%=name %>님<br>어서오세요!</h4>
						</a>
						<div id="logoutBtn" style="cursor:pointer;" onclick="logout()">
							logout
						</div>
					</div>
					<hr>
					<div class="menu">
						<div class="m1">
							학생 관리
							<ul class="m2">
								<li><a href="StudentAdd.do">학생 등록</a></li>
								<li><a href="StudentSelect.do">학생 조회</a></li>
							</ul>
						</div>
						<div class="m1">
							휴가 관리
							<ul class="m2">
								<li><a href="manager.do">최근 신청 내역</a></li>
								<li><a href="managerRestSelect.do">휴가 내역 조회</a></li>
							</ul>
						</div>
						<div class="m1">
							과목 관리
							<ul class="m2">
								<li><a href="SubjectSelect.do">과목 조회</a></li>
								<li><a href="SubjectAdd.do">과목 추가</a></li>
							</ul>
						</div>
					</div>
				</div>