<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
/* $("#print").click(function() {
	$("#report").printThis();
}) */

<p>// 현재 document.body의 html을 A4 크기에 맞춰 PDF로 변환
html2canvas(document.body, {
  onrendered: function(canvas) {
 
    // 캔버스를 이미지로 변환
    var imgData = canvas.toDataURL('image/png');
     
    var imgWidth = 210; // 이미지 가로 길이(mm) A4 기준
    var pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
    var imgHeight = canvas.height * imgWidth / canvas.width;
    var heightLeft = imgHeight;
     
        var doc = new jsPDF('p', 'mm');
        var position = 0;
         
        // 첫 페이지 출력
        doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
        heightLeft -= pageHeight;
         
        // 한 페이지 이상일 경우 루프 돌면서 출력
        while (heightLeft >= 20) {
          position = heightLeft - imgHeight;
          doc.addPage();
          doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
          heightLeft -= pageHeight;
        }
 
        // 파일 저장
        doc.save('sample_A4.pdf');
  }
});</p>


</script>

<style>
thead {color:green;}
tbody {color:blue;}
tfoot {color:red;}
table, th, td {
    border: 1px solid black;
}
</style>


</head>
<body>

<table>
  <colgroup>
    <col width="30%">
    <col width="70%">
  </colgroup>
  <thead>
    <tr>
      <th>itext</th>
      <th>테스트</th>
    </tr>
  </thead>
  <tfoot>
    <tr>
      <td>너무잘됨</td>
      <td>10000</td>
    </tr>
  </tfoot>
  <tbody>
    <tr>
      <td>맞는말임</td>
      <td>1000</td>
    </tr>
    <tr>
      <td>귀찮아</td>
      <td>100</td>
    </tr>
  </tbody>
</table>
 
<p><b>테스트 확인:</b> tbody</p>

<form name="">

<input type="submit" value="PDF만들기">
<input type="submit" value="인쇄하기">

</form>
 


</body>
</html>