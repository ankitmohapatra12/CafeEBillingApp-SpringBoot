package com.secureApp.SecureApp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.secureApp.SecureApp.entity.PdfDtaEntity;
import com.secureApp.SecureApp.service.BillingService;
import com.secureApp.SecureApp.service.PdfService;
/**
 * @author Ankit Mohapatra
 * 
 */
@RestController
public class GeneratePdfController {
	
	private static final Logger log = LoggerFactory.getLogger(GeneratePdfController.class);
	
	@Autowired
	private PdfService pdfService;
	
	@Autowired
	private BillingService billService;
	
	@GetMapping("/generateBillPdf/{bill_id}")
	public String generatePdf(@PathVariable("bill_id") long bill_id) {
		String message = "";
		
		PdfDtaEntity result  = billService.findBillById(bill_id); 
		if(result!=null) {
			message = pdfService.storeBillPdfData(result);
		}
		
		if(message.equalsIgnoreCase("SUCCESS")) {
			return "PDF stored successfully !";
		}
		message = "Failed to store PDF !";
		log.info("GeneratePdfController::generatePdf()");
		return message;
	}
}
