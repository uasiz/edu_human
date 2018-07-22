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
						<a href="MyPageStudent.jsp">
							<h3><%=name %>님<br>어서오세요!</h3>
						</a>
						<div id="logoutBtn" style="cursor: pointer;" onclick="logout()">logout</div>
					</div>
					<hr>
					
					<div class="menu">
						<div class="m1">
							휴가 관리
							<ul class="m2">
								<li><a href="stuLogin.do">휴가 신청</a></li>
								<li><a href="selectRestProcess.do">휴가 조회</a></li>
							</ul>
						</div>
					</div>
				</div>