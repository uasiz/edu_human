<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	String id = (String) session.getAttribute("id");
	if(session.getAttribute("id") == null) {
		pageContext.forward("loginForm.jsp");
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BananCola HumanMind 자유게시판</title>
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
<style>
        * {
            margin: 0px;
            padding: 0px;
        }
        
        body {
        	background: url("img/photo_bg.jpg") no-repeat center center fixed;
        	background-size: cover;
        }
        
        #wrap {
            position: relative;
            width:  980px;
            left: 50%;
            margin-left: -490px;
            margin-top: 50px;
        }
        
        #tableDiv {
        	margin-top: 300px;
            width: 980px;
        }
        
        table.type09 {
            border-collapse: collapse;
            text-align: left;
            line-height: 1.5;
        
        }
        #thNum {
            width: 80px;
            padding: 10px;
            font-weight: bold;
            vertical-align: top;
            border-bottom: 1px solid #ccc;
            border-top: 1px solid #ccc;
            background: #f3f6f7;
        }
        #thTitle {
            width: 370px;
            padding: 10px;
            font-weight: bold;
            vertical-align: top;
            border-bottom: 1px solid #ccc;
            border-top: 1px solid #ccc;
            background: #f3f6f7;
        }
        #thName {
            width: 100px;
            padding: 10px;
            font-weight: bold;
            vertical-align: top;
            border-bottom: 1px solid #ccc;
            border-top: 1px solid #ccc;
            background: #f3f6f7;
        }
        #thDate {
            width: 230px;
            padding: 10px;
            font-weight: bold;
            vertical-align: top;
            border-bottom: 1px solid #ccc;
            border-top: 1px solid #ccc;
            background: #f3f6f7;
        }
        #thHit {
            width: 50px;
            padding: 10px;
            font-weight: bold;
            vertical-align: top;
            border-bottom: 1px solid #ccc;
            border-top: 1px solid #ccc;
            background: #f3f6f7;
        }

        table.type09 td {
        	background: #fff;
            padding: 10px;
            vertical-align: top;
            border-bottom: 1px solid #ccc;
        }
        button{
          background:#675a53;
          color:#fff;
          border:none;
          position:relative;
          height:30px;
          font-size:1em;
          padding:0 2em;
          cursor:pointer;
          transition:800ms ease all;
          outline:none;
        }
        button:hover{
          background:#fff;
          color:rgb(32, 155, 226);
        }
        button:before,button:after{
          content:'';
          position:absolute;
          top:0;
          right:0;
          height:2px;
          width:0;
          background: rgb(9, 10, 59);
          transition:400ms ease all;
        }
        button:after{
          right:inherit;
          top:inherit;
          left:0;
          bottom:0;
        }
        button:hover:before,button:hover:after{
          width:100%;
          transition:800ms ease all;
        }
        #buttons {
            margin-top: 20px;
        }
        a:link {
        	color: black;
        }
        a:hover {
        	color: glay;
        	text-decoration: none;
        }
        a:visited {
        	color: black;
        }
        
        </style>
</head>
<body>
<div id="wrap">
    <div id="tableDiv">
	<table class="type09">
		<tr>
			<th id="thNum">번호</th>
			<th id="thTitle">제목</th>
			<th id="thName">작성자</th>
			<th id="thDate">날짜</th>
			<th id="thHit">조회수</th>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.bId}</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent}"> - </c:forEach>
				<a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a></td>
			<td>${dto.bName}</td>	
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
    </table>
    </div>
	<div id="buttons">
            <button type="button" id="modifyBtn" onclick="location.href='write_view.do'">글작성</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" id="logoutBtn">로그아웃</button>
    </div>
</div>
</body>
</html>