package com.secureApp.SecureApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secureApp.SecureApp.entity.PasswordResetToken;


/**
 * @author Ankit Mohapatra
 * 
 */


@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {

	PasswordResetToken findByToken(String token);

}
