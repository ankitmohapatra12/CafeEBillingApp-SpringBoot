package com.secureApp.SecureApp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.entity.PdfDtaEntity;
import com.secureApp.SecureApp.repository.PdfDataRepository;

/**
 * @author Ankit Mohapatra
 * 
 */
@Service
public class PdfServiceImpl implements PdfService {

	
	private static final Logger log = LoggerFactory.getLogger(PdfServiceImpl.class);
	
	@Autowired
	private PdfDataRepository pdfRepo;
	
	//storing bill pdf data
	@Override
	public String storeBillPdfData(PdfDtaEntity pdfData) {
		
		try {
			PdfDtaEntity pdfDataEntity = pdfRepo.save(pdfData);
			if(pdfDataEntity != null) {
				return "SUCCESS";
			}
		}catch (Exception e) {
			log.error("PdfServiceImpl::storeBillPdfData()",e.toString());
			return "FAILURE";
		}
		return "SUCCESS";
	}


	@Override
	public List<PdfDtaEntity> getPdfGeneratedDetails() {
		List<PdfDtaEntity> pdfData=null;
		try {
			pdfData = pdfRepo.findAll();
		}
		catch(Exception e) {
			log.error("PdfServiceImpl::getPdfGeneratedDetails()",e.toString());
			return pdfData;
		}
		return pdfData;
	}

}
