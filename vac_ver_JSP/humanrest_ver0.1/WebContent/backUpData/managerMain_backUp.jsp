<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String id = (String) session.getAttribute("id");
	if(session.getAttribute("id") == null) {
		pageContext.forward("loginForm.jsp");
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
 $(document).ready(function(){
	 
        $("#logoutBtn").click(function() {
        	
        	var result = confirm('정말로 로그아웃 하시겠습니까?');

        	if (result == true){
        		window.location.href = "loginForm.jsp";
        	}
            
        });
        		
 })
</script>
</head>
<body>
managerMain 페이지임다.
<br><br>
<input type="button" value="전체 내역 조회" onclick="location.href='managerRestSelect.do'"><br>
<input type="button" value="결재 완료 내역 조회" onclick="location.href='managerRestCompleteSelect.do'"><br>
<input type="button" value="결재 대기 내역 조회" onclick="location.href='managerRestWaitSelect.do'"><br>
<br><br>
<button type="button" id="logoutBtn">로그아웃</button>
</body>
</html>