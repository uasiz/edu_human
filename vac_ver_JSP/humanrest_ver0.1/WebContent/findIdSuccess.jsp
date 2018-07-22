<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.humanrest.database.StudentDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<%

	ArrayList<StudentDto> dtos = (ArrayList<StudentDto>) request.getAttribute("list");
	
	String id = dtos.get(0).getStu_number();
	String pass = dtos.get(0).getStu_password();

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
        .wrap{
            text-align: center;
            margin:10px;
            height:110px;
            border-radius: 20px;
            background-color:white;
        }
        table{
            width:100%;
            height:100%;
            font-weight: bold;
        }
        .info{
            color:#999;
        }
        input{
            outline: none;
            border: none;
            cursor: pointer;
            width:30px;
        }
        </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID/Password</title>
</head>
<body>
<div class='wrap'>
    <table>
        <tr>
            <td>id</td>
            <td class='info'><%=id%></td>
        </tr>
        <tr>
            <td>pwd</td>
            <td class='info'><%=pass%></td>
        </tr>
        <tr>
            <td colspan="2"><input type='image' src='images/cancle.png' onclick="self.close()"></td>
        </tr>
    </table>
</div>
</body>
</html>