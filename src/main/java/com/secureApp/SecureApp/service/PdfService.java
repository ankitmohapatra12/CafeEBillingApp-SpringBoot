package com.secureApp.SecureApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.entity.PdfDtaEntity;

/**
 * @author Ankit Mohapatra
 * 
 */
@Service
public interface PdfService {

	String storeBillPdfData(PdfDtaEntity result);

	List<PdfDtaEntity> getPdfGeneratedDetails();

}
