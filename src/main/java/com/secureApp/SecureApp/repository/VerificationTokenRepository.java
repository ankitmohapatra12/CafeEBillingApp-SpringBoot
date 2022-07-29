package com.secureApp.SecureApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secureApp.SecureApp.entity.VerificationToken;


/**
 * @author Ankit Mohapatra
 * 
 */

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);

}
