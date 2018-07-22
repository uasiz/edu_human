package com.humanrest.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ParseHtml12 {
	
	  public static final String DEST = "results/xmlworker/test2015-11.pdf";
	    public static final String HTML = "resources/xml/test2015-11.html";
	 
	    public static void main(String[] args) throws IOException, DocumentException {
	        File file = new File(DEST);
	        file.getParentFile().mkdirs();
	        new ParseHtml12().createPdf(DEST);
	    }
	    
	    public void createPdf(String file) throws IOException, DocumentException {
	        // step 1
	        Document document = new Document();
	        // step 2
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
	        writer.setInitialLeading(12);
	        // step 3
	        document.open();
	        // step 4
	        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
	                new FileInputStream(HTML));
	        // step 5
	        document.close();
	    }

}
