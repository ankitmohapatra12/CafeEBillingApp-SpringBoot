package com.secureApp.SecureApp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secureApp.SecureApp.entity.BillCreationEntity;
/**
 * @author Ankit Mohapatra
 * 
 */

@Repository
@Transactional
public interface BillingRepository extends JpaRepository<BillCreationEntity, Long> {

	
}
