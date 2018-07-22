package com.humanrest.excel;


import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*; 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestExcel {
	public static void main(String[] args) throws Exception {
		Workbook[] wbs = new Workbook[]
		 { new HSSFWorkbook(), new XSSFWorkbook() }; 
 
	 	for (int i = 0; i < wbs.length; i++) {
     	 		Workbook wb = wbs[i];
      			Sheet s = wb.createSheet("김준석"); //시트 생성
 			// Save
      			String filename = "두개생성.xls"; //파일이름
      			if (wb instanceof XSSFWorkbook) {
            			filename = filename + "x";
       			}
       			
			FileOutputStream out = new FileOutputStream(
			"D:\\" + filename); //파일위치
    			 wb.write(out);
       			out.close();
	 	}

}}
