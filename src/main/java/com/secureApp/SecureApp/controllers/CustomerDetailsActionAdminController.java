package com.secureApp.SecureApp.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secureApp.SecureApp.entity.CustomerRegistration;
import com.secureApp.SecureApp.service.CustomerRegistrationService;



/**
 * @author Ankit Mohapatra
 * 
 */



@RestController
public class CustomerDetailsActionAdminController {

	@Autowired
	private CustomerRegistrationService customerRegistrationService;

	
	
	@GetMapping(value="/customers")
	public ResponseEntity<Collection<CustomerRegistration>> getAllCustomers(){
		return new ResponseEntity<>(customerRegistrationService.findAllCustomers(),HttpStatus.OK);
	}
	
	
	//getting customer data for updation
	@GetMapping("/customerDetail/{id}")
	public CustomerRegistration GetCustomerDataForUpdate(@PathVariable("id") long customer_id) {
		
		CustomerRegistration customer_detail = customerRegistrationService.findByIdNotDeleted(customer_id);
		return customer_detail;
		
	}
	
	
	//updating customer data in database
	@PostMapping("/editCustomerDetails")
	public String updatingCustomerRegistrationData(@RequestBody CustomerRegistration customerRegistration) {
		String result ="";
		
		result  = customerRegistrationService.updateCustomer(customerRegistration);

		return result;
	}
	
	
	//delete customers
	@GetMapping("/deleteCustomerDetail/{id}")
	public String DisableCustomer(@PathVariable("id") long customer_id) {
		
		
		String result = customerRegistrationService.deleteCustomer(customer_id);
		
		return result;
		
	}
	
	
	
	@GetMapping("/getCustomerEmails")
	public ArrayList<String> getCustomerEmails() {
		return customerRegistrationService.findAllCustomersEmails();
	}
	
	

	@GetMapping("/getCustomerDetailsFromEmails/{email}")
	public CustomerRegistration getDetailsFromEmail(@PathVariable("email") String customer_email) {
		CustomerRegistration customer_detail = customerRegistrationService.findByEmailNotDeleted(customer_email);
		return customer_detail;
	}
	
	

	
	
	
	
	
	
	
	
	
	
}
