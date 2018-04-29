package com.jrxml.pdf.report;


import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/* Read The Step of this class
1. open and compile jrxml file 
2. print jrxml file
3. export to pdf file
4. thanks

*/

public class JRXMLGenerator {
	
	//list global variable initialized declaration
	JRDataSource dataSource = null;
	Map<String, Object> parameters = null;
	boolean result = false;
	
	public void generateReportPdfFile (Object param) {
		JasperReport jrxmlFile = null;
    	JasperPrint jasperPrint = null;
//    	File file = null;
    	 	
    	try {
    		
	    	//list object request to memory	
	    	parameters =  new HashMap<String, Object>();
	    	dataSource = new JREmptyDataSource();
	    	
	    	
	    	//this method open and compile jrxml file	
	    	jrxmlFile = compileJRXMLFile (jrxmlFile,"D:/Document/jrxml/report2.jrxml");
	    	//this method print jrxml file	   	
	    	jasperPrint = printJRXMLFile(jasperPrint, jrxmlFile);
			// this method export to PDF File.
	    	exportReportToPdfFile(jasperPrint, "C:/jasperoutput/StyledTextReport.pdf");
        
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println("Done!");
    }
    
    
    private JasperReport compileJRXMLFile (JasperReport jrxmlFile, String path)throws JRException, Exception{
    	
    	try {
    		jrxmlFile	= JasperCompileManager.compileReport(path);
		} catch (JRException e) {
			throw new JRException(e.getMessage());
		} catch (Exception e) {
			throw new JRException(e.getMessage());
		}
    	return jrxmlFile;
	
    }
    
    private JasperPrint printJRXMLFile (JasperPrint jasperPrint, JasperReport jrxmlFile)throws JRException, Exception{
    	
    	try {
    		jasperPrint = JasperFillManager.fillReport(jrxmlFile,
    		        parameters, dataSource);
    		
    	} catch (JRException e) {
			throw new JRException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    	return jasperPrint;
    }
   
    
    private void exportReportToPdfFile (JasperPrint jasperPrint, String path)throws JRException, Exception{
    	
    	try {
    		JasperExportManager.exportReportToPdfFile(jasperPrint,path
                   );
		} catch (JRException e) {
			throw new JRException(e.getMessage());
		} catch (Exception e) {
			throw new JRException(e.getMessage());
		}
    }

}
