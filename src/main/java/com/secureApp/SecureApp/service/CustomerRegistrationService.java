package com.secureApp.SecureApp.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.entity.CustomerRegistration;


/**
 * @author Ankit Mohapatra
 * 
 */

@Service
public interface CustomerRegistrationService {

	

	String saveCustomerDetails(CustomerRegistration customer);
	
	Collection<CustomerRegistration> findAllCustomers();

	CustomerRegistration findByIdNotDeleted(long customer_id);

	String updateCustomer(CustomerRegistration customerRegistration);

	String deleteCustomer(long customer_id);

	ArrayList<String> findAllCustomersEmails();

	CustomerRegistration findByEmailNotDeleted(String customer_email);
	
}
