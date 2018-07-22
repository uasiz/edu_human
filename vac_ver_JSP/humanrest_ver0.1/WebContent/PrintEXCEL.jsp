<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
    path = "";
    var excApp;
    var excBook;
    function xlsLoad(){
            //웹서버에 올릴시엔 ActiveXObject("Excel.Application");
            excApp = new ActiveXObject("Excel.Application");
            path = "C:\\naver\\counter.xls";
            excBook = excApp.workbooks.open(path)
            excApp.Visible = true;
              excApp.UserControl = true;            
            
    }
    function xlsCounter(Num)
    {   
        if (excBook.ActiveSheet.Cells(2,1).value == null) {
            excBook.ActiveSheet.Cells(1,1).value = "Count1";
            excBook.ActiveSheet.Cells(1,2).value = "Count2";
            excBook.ActiveSheet.Cells(1,3).value = "Count3";
            excBook.ActiveSheet.Cells(2,1).value = 0;
            excBook.ActiveSheet.Cells(2,2).value = 0;
            excBook.ActiveSheet.Cells(2,3).value = 0;
            
            excBook.ActiveSheet.Cells(2,Num).value += 1;
        } else {
            switch(Num)
            {
                case 1:
                    var count = excBook.ActiveSheet.Cells(2,1).value + 1;
                    excBook.ActiveSheet.Cells(2,1).value = count;
                    break;
                case 2:
                    var count = excBook.ActiveSheet.Cells(2,2).value + 1;
                    excBook.ActiveSheet.Cells(2,2).value = count;
                    break;
                case 3:
                    var count = excBook.ActiveSheet.Cells(2,3).value + 1;
                    excBook.ActiveSheet.Cells(2,3).value = count;
                    break;
            }
        }
    }
    function xlsClose()
    {
        excBook.Save();
        alert("save");
        excApp.Application.Quit();
        alert("Quit");
    }
</script>
</head>

<body onload="xlsLoad()" onunload="xlsClose()">
<form>
<img src="http://cafefiles.naver.net/data39/2008/12/29/231/%B1%D9%C7%CF%BD%C5%B3%E2_rozeny.png" onclick="xlsCounter(1)" width=452 height=202 alt="샘플">
</form>
</body>
</html>
