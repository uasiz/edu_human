<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.humanrest.util.Base64"%>
<%@page import="java.io.*"%>
<%@page import="java.awt.image.*"%>
<%@page import="javax.imageio.*"%>
<%@ page import="java.util.*"%>
<%
    request.setCharacterEncoding("utf-8");
    String base64Str = request.getParameter("imgBase64");
    String stuNum = request.getParameter("stuNum");
    String nowTime = request.getParameter("nowTime");
    String fileName = stuNum + "_" + nowTime + ".png";
    
    String path = "/signs/"+fileName;
    
    base64Str = base64Str.substring(base64Str.indexOf(",")+1, base64Str.length());
     
    Base64.decodeToFile(base64Str, "D:/HumanRest_ver0.1/WebContent/signs/"+fileName); //jpg,png ok
     
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

