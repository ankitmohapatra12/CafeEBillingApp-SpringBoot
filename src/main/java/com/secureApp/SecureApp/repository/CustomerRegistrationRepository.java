package com.secureApp.SecureApp.repository;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.secureApp.SecureApp.entity.CustomerRegistration;



/**
 * @author Ankit Mohapatra
 * 
 */

@Repository
@Transactional
public interface CustomerRegistrationRepository extends JpaRepository<CustomerRegistration,Long> {

	@Modifying
	@Query(value = "update customer_details c set c.isDeleted=1 where c.customer_id = ?",nativeQuery = true)
	int DeleteCustomerById(long customer_id);

	@Query(value= "SELECT * from customer_details c where c.isDeleted= 0 and c.customer_id = ?",nativeQuery = true)
	CustomerRegistration findByIdNotDeleted(long customer_id);

	@Query(value= "SELECT * from customer_details c where c.isDeleted = 0",nativeQuery = true)
	Collection<CustomerRegistration> findAllCustomersNotDeleted();
	
	@Query(value= "SELECT customer_id,customer_name,customer_email,customer_phone,customer_username from customer_details",nativeQuery = true)
	Collection<CustomerRegistration> findAllCustomersForBills();

	@Query(value= "SELECT customer_email from customer_details c where c.isDeleted = 0",nativeQuery = true)
	ArrayList<String> findAllCustomersEmails();

	@Query(value= "SELECT * from customer_details c where c.isDeleted= 0 and c.customer_email = ?",nativeQuery = true)
	CustomerRegistration findByEmailNotDeleted(String customer_email);

}
