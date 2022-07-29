package com.secureApp.SecureApp.repository;

/**
 * @author Ankit Mohapatra
 * 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secureApp.SecureApp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
}
