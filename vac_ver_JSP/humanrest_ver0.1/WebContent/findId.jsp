<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style>
        
textarea:focus, input:focus {
  border-color: transparent !important;
}

input:focus::-webkit-input-placeholder { color:transparent; }
input:focus:-moz-placeholder { color:transparent; }
input:focus::-moz-placeholder { color:transparent; }
input:focus:-ms-input-placeholder { color:transparent; }

input::-webkit-input-placeholder { color: #999999; }
input:-moz-placeholder { color: #999999; }
input::-moz-placeholder { color: #999999; }
input:-ms-input-placeholder { color: #999999; }
        body{
            width:300px;
            height:130px;
            margin:0 auto;
            padding:0 auto;
            display:table;
            background: #9053c7;
            background: -webkit-linear-gradient(-135deg, #c850c0, #4158d0);
            background: -o-linear-gradient(-135deg, #c850c0, #4158d0);
            background: -moz-linear-gradient(-135deg, #c850c0, #4158d0);
            background: linear-gradient(-135deg, #c850c0, #4158d0);
        }
        input {
            outline: none;
            border: none;
            background: #e6e6e6;
            text-align:center;
        }
        form {
            text-align:center;
            margin:10px 8px 10px 8px;
            background: white;
            border-radius: 20px;
            padding-top:10px;
            padding-bottom:10px;
        }
        form div{
            font-weight: bold;
            padding:2px 2px 5px 2px;
            background: #e6e6e6;
            border-radius: 50px;
        }
        form div:nth-child(2) input{
            width:50px;
        }
        form .btn,form div{
            margin:5px;
        }
        .btn{
            background: white;
            width:20px;
            height:20px;
            border-bottom: 1px solid white;
        }
        .btn:hover{
            border-bottom: 1px solid gray;
        }
        </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID/Password 확인</title>
</head>
<body>
<div class='wrap'>
    <form action="findMyId.do" method="post">
        <div>name <input type='text' placeholder="성+이름" name="stuName"></div>
        <div>phone 
            <input type='text' maxlength="3" name="phone1">-
            <input type='text' maxlength="4" name="phone2">-
            <input type='text' maxlength="4" name="phone3">
        </div>
        <input class='btn' type="image" src='images/consent.png' name='submit' value="" title='찾기'>
        <input class='btn' type="image" src='images/cancle.png' onclick="self.close()" title='취소'>
    </form>
</div>
</body>
</html>
<script>
	
</script>