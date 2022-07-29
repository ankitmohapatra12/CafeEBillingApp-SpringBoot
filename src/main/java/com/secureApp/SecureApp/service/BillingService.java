package com.secureApp.SecureApp.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.DTO.BillCreation;
import com.secureApp.SecureApp.entity.PdfDtaEntity;

/**
 * @author Ankit Mohapatra
 * 
 */
@Service
public interface BillingService {

	
	String createBill(BillCreation billCreation);

	Collection<BillCreation> getAllBills();

	PdfDtaEntity findBillById(long bill_id);
}
