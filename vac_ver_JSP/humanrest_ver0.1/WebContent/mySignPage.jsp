<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<%
	String id = (String) session.getAttribute("id");
	if (session.getAttribute("id") == null) {
		pageContext.forward("loginForm.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>서명을 입력하세요.</title>
    <style>
        input{
            cursor: pointer;
            outline: none;
            border: none;
            background-color: white;
            margin:0px 20px 0px 19px;
            color:#333;
            border-bottom : 1px solid #eee;
        }
        input:nth-child(2){
            float:right;
        }
        div{
            width:200px;
        }
        canvas{
        }
    </style>
     <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
    	var stuNum = '<%=id%>';
    	var nowTime = new Date().getTime();
    	var fileName = stuNum + "_" + nowTime + ".png";
    	var path = "signs/"+fileName;
        var pos = {
            drawable: false,
            x: -1,
            y: -1
        };
        var canvas, ctx;

        window.onload = function () {
            canvas = document.getElementById("canvas");
            ctx = canvas.getContext("2d");

            canvas.addEventListener("mousedown", listener);
            canvas.addEventListener("mousemove", listener);
            canvas.addEventListener("mouseup", listener);
            canvas.addEventListener("mouseout", listener);
        }

        function listener(event) {
            switch (event.type) {
                case "mousedown":
                    initDraw(event);
                    break;

                case "mousemove":
                    if (pos.drawable)
                        draw(event);
                    break;

                case "mouseout":
                case "mouseup":
                    finishDraw();
                    break;
            }
        }
        function initDraw(event) {
            ctx.beginPath();
            pos.drawable = true;
            var coors = getPosition(event);
            pos.X = coors.X;
            pos.Y = coors.Y;
            ctx.moveTo(pos.X, pos.Y);
        }

        function draw(event) {
            var coors = getPosition(event);
            ctx.lineTo(coors.X, coors.Y);
            pos.X = coors.X;
            pos.Y = coors.Y;
            ctx.stroke();
        }

        function finishDraw() {
            pos.drawable = false;
            pos.X = -1;
            pos.Y = -1;
        }

        function getPosition(event) {
            var x = event.pageX - canvas.offsetLeft;
            var y = event.pageY - canvas.offsetTop;
            return { X: x, Y: y };
        }
        function clearCanvas() {
            // canvas
            var cnvs = document.getElementById('canvas');
            // context
            var ctx = canvas.getContext('2d');

            // 픽셀 정리
            ctx.clearRect(0, 0, cnvs.width, cnvs.height);
            // 컨텍스트 리셋
            ctx.beginPath();
        }
        function uploadCanvas() {
            var canvas2 = document.getElementById('canvas');
            var dataURL = canvas2.toDataURL();//이미지 데이터가 base64 문자열로 인코딩된 데이터
            $.ajax({
              type: "POST",
              url: "saveBase64.jsp",
              contentType: "application/x-www-form-urlencoded; charset=utf-8",
              data: { "imgBase64": dataURL, "stuNum": stuNum, "nowTime": nowTime, },
              success: function() {
        	        var du = window.opener;
        	          
        	        du.changeImg(path);
        	        //du.document.userinput.mySign.value = path;
        	        
        	        window.close();
              }
            })
        }
        //<input type="image" src="images/undo.png" title="Clear" onClick="clearCanvas()">
        //<input type="image" src="images/consent.png" title="Up Load" onClick="uploadCanvas()">
    </script>
    <style type="text/css">
        canvas {
            border: 1px solid black
        }
    </style>

</head>

<body>
    <div>
        <canvas id="canvas" width="200" height="60">
        </canvas>
    </div>
    <div>
        <input type='button' value='new' onClick="clearCanvas()">
        <input type="button" value='confirm' onClick="uploadCanvas()">
    </div>


</body>

</html>