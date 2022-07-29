package com.secureApp.SecureApp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.entity.CustomerRegistration;
import com.secureApp.SecureApp.repository.CustomerRegistrationRepository;


/**
 * @author Ankit Mohapatra
 * 
 */

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService{
	
	
	private static final Logger log = LoggerFactory.getLogger(CustomerRegistrationServiceImpl.class);
	
	@Autowired
	CustomerRegistrationRepository cust_repo;
	
	//saving customer details 
	
	public String saveCustomerDetails(CustomerRegistration customerRegistration) {
		
		 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                 "[a-zA-Z0-9_+&*-]+)*@" +
                 "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                 "A-Z]{2,7}$";
		if(!customerRegistration.getCustomer_name().isEmpty()) { 
			if(!customerRegistration.getCustomer_name().matches("[a-zA-Z]+")) {
				return "Please Enter only Alphabets !!";
			}
			if(customerRegistration.getCustomer_name().length()>30) {
				return "Length cannot exceeed 30 Characters !!";
			}
		}
		else {
			return "Customer name cannot be empty";
		}
		
		if(!customerRegistration.getCustomer_email().isEmpty()) {
			Pattern pat = Pattern.compile(emailRegex);
			if(!(pat.matcher(customerRegistration.getCustomer_email()).matches())) {
				return "Please enter a valid email";
			}
			if(customerRegistration.getCustomer_email().length()>50) {
				return "Length cannot exceeed 50 Characters !!";
			}
		}else { return "Customer email cannot be empty!"; }
		
		
		if(!customerRegistration.getCustomer_phone().isEmpty()) {
			if(!customerRegistration.getCustomer_phone().matches("\\+\\d(-\\d{3}){2}-\\d{4}")) {
				return "Please enter a valid contact number !";
			}
			if(customerRegistration.getCustomer_phone().length()>13) {
				return "Length cannot exceeed 13 Characters !!";
			}
		}else { return "Customer contact number cannot be empty!"; }
		
		
		if(!customerRegistration.getCustomer_username().isEmpty()) {
			if(!customerRegistration.getCustomer_username().matches("a-zA-Z0-9"))
			{
				return "Please enter a valid username (avoid using special characters)";
			}
			if(customerRegistration.getCustomer_username().length()>30) {
				return "Length cannot exceeed 30 Characters !!";
			}
		}else { return "Customer username cannot be empty!"; }
		customerRegistration.setCustomer_name(customerRegistration.getCustomer_name().trim());
		customerRegistration.setCustomer_email(customerRegistration.getCustomer_email().trim());
		customerRegistration.setCustomer_phone(customerRegistration.getCustomer_phone().trim());
		customerRegistration.setCustomer_username(customerRegistration.getCustomer_username().trim());
		
		try {
			cust_repo.save(customerRegistration);
		}catch (Exception e) {
			log.error("CustomerRegistrationServiceImpl::saveCustomerDetails()",e.toString());
			return "Failed to save customer details !!";
		}
		return "Successfully saved customer !!";
	}
	
	public Collection<CustomerRegistration> findAllCustomers() {
		Collection<CustomerRegistration> customer = null;
		try {
			  customer = cust_repo.findAllCustomersForBills();
		}catch (Exception e) {
			log.error("CustomerRegistrationServiceImpl::findAllCustomers()",e.toString());
			return null;
		}
		return customer;
	}

	@Override
	public CustomerRegistration findByIdNotDeleted(long customer_id) {
		CustomerRegistration customer_details;
		try {
			customer_details =  cust_repo.findByIdNotDeleted(customer_id);
		}
		catch(Exception e) {
			log.error("CustomerRegistrationServiceImpl::findByIdNotDeleted()",e.toString());
			return null;
		}
		return customer_details;
	}

	@Override
	public String updateCustomer(CustomerRegistration customerRegistration) {
		 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                 "[a-zA-Z0-9_+&*-]+)*@" +
                 "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                 "A-Z]{2,7}$";
		 
		 if(!customerRegistration.getCustomer_name().isEmpty()) { 
				if(!customerRegistration.getCustomer_name().matches("[a-zA-Z]+")) {
					return "Please Enter only Alphabets !!";
				}
				if(customerRegistration.getCustomer_name().length()>30) {
					return "Length cannot exceeed 30 Characters !!";
				}
			}
			else {
				return "Customer name cannot be empty";
			}
			
			if(!customerRegistration.getCustomer_email().isEmpty()) {
				Pattern pat = Pattern.compile(emailRegex);
				if(!(pat.matcher(customerRegistration.getCustomer_email()).matches())) {
					return "Please enter a valid email";
				}
				if(customerRegistration.getCustomer_email().length()>50) {
					return "Length cannot exceeed 50 Characters !!";
				}
			}else { return "Customer email cannot be empty!"; }
			
			
			if(!customerRegistration.getCustomer_phone().isEmpty()) {
				if(!customerRegistration.getCustomer_phone().matches("\\+\\d(-\\d{3}){2}-\\d{4}")) {
					return "Please enter a valid contact number !";
				}
				if(customerRegistration.getCustomer_phone().length()>13) {
					return "Length cannot exceeed 13 Characters !!";
				}
			}else { return "Customer contact number cannot be empty!"; }
			
			
			if(!customerRegistration.getCustomer_username().isEmpty()) {
				if(!customerRegistration.getCustomer_username().matches("a-zA-Z0-9"))
				{
					return "Please enter a valid username (avoid using special characters)";
				}
				if(customerRegistration.getCustomer_username().length()>30) {
					return "Length cannot exceeed 30 Characters !!";
				}
			}else { return "Customer username cannot be empty!"; }
		customerRegistration.setCustomer_name(customerRegistration.getCustomer_name().trim());
		customerRegistration.setCustomer_email(customerRegistration.getCustomer_email().trim());
		customerRegistration.setCustomer_phone(customerRegistration.getCustomer_phone().trim());
		customerRegistration.setCustomer_username(customerRegistration.getCustomer_username().trim());
		try {
			CustomerRegistration customer = cust_repo.save(customerRegistration);
			if(customer==null) {
				return "Failure to update customer";
			}
		}
		catch(Exception e ) {
			log.error("CustomerRegistrationServiceImpl::updateCustomer()",e.toString());
			return "Failure to update customer";
		}
		
		return "Successfully updated customer";
		
	}

	@Override
	public String deleteCustomer(long customer_id) {
			try {
				int i = cust_repo.DeleteCustomerById(customer_id);
				if(i<=0) {
					return "Failed to delete customer";
				}
			}
			catch(NoSuchElementException e) {
				log.error("CustomerRegistrationServiceImpl::deleteCustomer()",e.toString());
				return "Failed to delete customer";
			}
		return "Successfully deleted customer";
	}

	@Override
	public ArrayList<String> findAllCustomersEmails() {
		// TODO Auto-generated method stub
		ArrayList<String> CustomerEmails;
		try {
			CustomerEmails = cust_repo.findAllCustomersEmails();
		}
		catch(Exception e) {
			log.error("CustomerRegistrationServiceImpl::findAllCustomersEmails()",e.toString());
			return null;
		}
		return CustomerEmails;
	}

	@Override
	public CustomerRegistration findByEmailNotDeleted(String customer_email) {
        
		
		
		
		CustomerRegistration customer_details;
		try {
		customer_details =  cust_repo.findByEmailNotDeleted(customer_email);
		}
		catch(NoSuchElementException e) {
			log.error("CustomerRegistrationServiceImpl::findByEmailNotDeleted()",e.toString());
			return null;
		}
		return customer_details;
	}
	
	
	
	
	
}
