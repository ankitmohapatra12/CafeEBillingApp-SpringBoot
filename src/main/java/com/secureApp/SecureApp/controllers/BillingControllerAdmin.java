package com.secureApp.SecureApp.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secureApp.SecureApp.DTO.BillCreation;
import com.secureApp.SecureApp.entity.CustomerRegistration;
import com.secureApp.SecureApp.service.BillingService;
import com.secureApp.SecureApp.service.CustomerRegistrationService;


/**
 * @author Ankit Mohapatra
 * 
 */


@RestController
public class BillingControllerAdmin {
	
	

	
	@Autowired
	private BillingService billingService;
	
	@Autowired
	private CustomerRegistrationService customerService;
	
	//Getting all customers for Bill;
	@GetMapping("/customersForBill")
	public ResponseEntity<Collection<CustomerRegistration>> getCustomerUsername(){
		return new ResponseEntity<>(customerService.findAllCustomers(),HttpStatus.OK);	
	}
	
	//Creating bill
	@PostMapping("/createBill")
	public String CreateBill(@RequestBody BillCreation billCreation ) {
		String message = billingService.createBill(billCreation);
		return message;
	}
	
	
	//getting all bills
	@GetMapping("/bills")
	public ResponseEntity<Collection<BillCreation>> getAllBill(){
		return new ResponseEntity<>(billingService.getAllBills(),HttpStatus.OK);
	}

}
