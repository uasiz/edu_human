<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="js/html2canvas.min.js"></script>
<script src="js/jspdf.min.js"></script>
<script src="js/bluebird.min.js"></script>



<script>

function createPDF(){
	html2canvas(document.body, {
		  onrendered: function(canvas) {

		    var imgData = canvas.toDataURL('image/png');
		     
		    var imgWidth = 210; // 210 이미지 가로 길이 A4
		    var pageHeight = imgWidth * 900;  //1.414 출력 페이지 세로 길이 계산 A4
		    var imgHeight = canvas.height * imgWidth / canvas.width;
		    var heightLeft = imgHeight;
		     
		        var doc = new jsPDF('p', 'mm');
		        var position = 0;

		        doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
		        heightLeft -= pageHeight;

		        while (heightLeft >= 20) {
		          position = heightLeft - imgHeight;
		          doc.addPage();
		          doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
		          heightLeft -= pageHeight;
		        }
		        doc.save('printing.pdf');
		  }
		});
}

</script>

</head>
<body>


	<h1>휴가신청서</h1>

	<br>
	<br>
	<br>

	<h3>■ 신 청 인 :</h3>
	<h3>■ 생년월일 :</h3>
	<h3>■ 휴가사용현황 :</h3>

	<table border="1">
		<tr>
			<th>훈련과정명</th>
			<th>//////////</th>
		</tr>
		<tr>
			<th>훈련기간(6개월)</th>
			<th>//////////</th>
		</tr>
		<tr>
			<th>1일 훈련시간(총7.8시간)</th>
			<th>//////////</th>
		</tr>
		<tr>
			<th>사용가능 휴가일수</th>
			<th>//////////</th>
		</tr>
		<th>기사용한 휴가일수</th>
		<th>//////////</th>
		</tr>
		<th>금회 신청 휴가일수</th>
		<th>//////////</th>
		</tr>
		<th>잔여 휴가일수</th>
		<th>//////////</th>
		</tr>
		<table>


			<h4>※ 휴가*신청은 부득이 한 경우를 제외하고는 사전 신청</h4>
			<h6>*6개월 이상, 1주 5일 및 1일 평균훈련시간이 6시간 이상 과정에 한함</h6>


			위와 같이 휴가 신청서를 제출합니다.
			<br> 년 월 일
			<br> 신청인 (인)
			<br>
			<h1>휴 먼 교 육 센 터</h1>
			<h3>(직인)</h3>

			<br>
			<br>
			<br>
			<br>
			<br>
			<br>

			<div style="cursor: pointer;" onclick="createPDF()">PDF만들기(가칭)</div>
			<!-- window.open('', '', 'width=800,height=1100'); -->
			<div style="cursor: pointer;" onclick="window.print()">인쇄하기(가칭)</div>
			<div style="cursor: pointer;" id="editor">테스트(가칭)</div>
			<div style="cursor: pointer;" id="submit">테스트2(가칭)</div>
			<div style="cursor: pointer;" onclick="window.open("
				createPDF()", 'width=800,height=1100')">테스트1(가칭)</div>
</body>
</html>