package com.secureApp.SecureApp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.Model.UserModel;
import com.secureApp.SecureApp.entity.User;
import com.secureApp.SecureApp.entity.VerificationToken;


/**
 * @author Ankit Mohapatra
 * 
 */

@Service
public interface UserService {

	User registerUser(UserModel userModel);

	void saveVerificationTokenForUser(String token, User user);

	String validateVerificationToken(String token);

	VerificationToken generatedNewVerificationToken(String oldToken);

	void createPasswordResetTokenForUser(User user, String token);

	User findUserByEmail(String email);

	String validatePasswordResetToken(String token);

	Optional<User> getUserByPasswordResetToken(String token);

	void changePassword(User user, String newPassword);

	boolean checkIfValidOldPassword(User user, String oldPassword);

}
