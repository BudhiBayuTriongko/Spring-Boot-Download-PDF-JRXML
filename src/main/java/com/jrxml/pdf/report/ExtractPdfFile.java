package com.jrxml.pdf.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public class ExtractPdfFile {
	@SuppressWarnings({"rawtypes","resource"})
	 public StreamingResponseBody getSteamingFile( @RequestBody Map param, HttpServletResponse response) throws IOException{
	  
	   response.setContentType("application/pdf");
       response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");    
       
		InputStream inputStream = new FileInputStream(new File("C:\\jasperoutput/StyledTextReport.pdf"));
	       return outputStream -> {
	           int nRead;
	           byte[] data = new byte[1024];
	           while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
	               System.out.println("Writing some bytes..");
	               outputStream.write(data, 0, nRead);
	           }
	       };
    }
}
