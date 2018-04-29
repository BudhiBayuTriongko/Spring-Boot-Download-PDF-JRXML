package com.jrxml.pdf.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.jrxml.pdf.report.JRXMLGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

@SuppressWarnings({"resource","rawtypes"})
@RestController  // Also possible to @Controller here
public class StreamingResponseBodyController {
    @RequestMapping(value = "downloadFile", method = RequestMethod.POST)
    public StreamingResponseBody getSteamingFile( @RequestBody Map param,HttpServletResponse response) throws IOException {
    	
    	JRXMLGenerator jrxmlGenerator = new JRXMLGenerator();
    	jrxmlGenerator.generateReportPdfFile(param);
    	
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