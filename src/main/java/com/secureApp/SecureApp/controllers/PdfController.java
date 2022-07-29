package com.secureApp.SecureApp.controllers;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.secureApp.SecureApp.Constants.FileStorageLocationConstants;
import com.secureApp.SecureApp.entity.PdfDtaEntity;
import com.secureApp.SecureApp.service.PdfService;
/**
 * @author Ankit Mohapatra
 * 
 */
@RestController
public class PdfController {

	private static final Logger log = LoggerFactory.getLogger(PdfController.class);
	
	@Autowired
	private PdfService pdfService;
	
	
	
	@GetMapping("/pdfData")
	public Collection<PdfDtaEntity> getPdfGenerated(){
		
		List<PdfDtaEntity> pdfDetails = pdfService.getPdfGeneratedDetails();
		return pdfDetails;
		
	}
	
	
	
	  @GetMapping("/getPdf/{pdfName}")
	  public void downloadImageFile(@PathVariable("pdfName") String pdfName, HttpServletResponse response) 
	  {
			response.setHeader("Content-Disposition","attachment; pdfName="+pdfName);
			response.setHeader("Content-Transfer-Encoding", "binary");
			try {
				BufferedOutputStream bos =  new BufferedOutputStream(response.getOutputStream());
				FileInputStream fis = new FileInputStream(FileStorageLocationConstants.PathUrl);
				int len;
				byte[] buf = new byte[1024];
				while((len = fis.read(buf)) > 0) {
					bos.write(buf,0,len);
				}
				fis.close();
				bos.close();
				response.flushBuffer();
			}
			catch(IOException e) {
				log.error("PdfController::downloadImageFile()",e.toString());
			}	
	   }
	
}
