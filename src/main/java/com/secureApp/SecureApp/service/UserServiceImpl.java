package com.secureApp.SecureApp.service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.Model.UserModel;
import com.secureApp.SecureApp.entity.PasswordResetToken;
import com.secureApp.SecureApp.entity.User;
import com.secureApp.SecureApp.entity.VerificationToken;
import com.secureApp.SecureApp.repository.PasswordResetTokenRepository;
import com.secureApp.SecureApp.repository.UserRepository;
import com.secureApp.SecureApp.repository.VerificationTokenRepository;



/**
 * @author Ankit Mohapatra
 * 
 */

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	
	@Override
	public User registerUser(UserModel userModel) {
		User user = new User();
		user.setEmail(userModel.getEmail());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setRole("ADMIN");
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		try {
		userRepository.save(user);
		}catch (Exception e) { log.error("UserServiceImpl::registerUser()",e.toString());return null;}
		return user;
	}


	@Override
	public void saveVerificationTokenForUser(String token, User user) {
	
		VerificationToken verificationToken =  new VerificationToken(user,token);
		try {
		verificationTokenRepository.save(verificationToken);
		}catch(Exception e) { log.error("UserServiceImpl::saveVerificationTokenForUser()",e.toString()); }
		
	}


	@Override
	public String validateVerificationToken(String token) {
		try {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
		if(verificationToken == null) {
			return "Invalid Token";
		}
		
		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		
		if((verificationToken.getExpirationTime().getTime() - cal.getTime().getTime()) <=0) {
			verificationTokenRepository.delete(verificationToken);
			return "Token Expired !";
		}
		
		
		user.setEnabled(true);
		userRepository.save(user);
		}catch(Exception e) { log.error("UserServiceImpl::validateVerificationToken()",e.toString()); }
		return "Valid";
	}


	@Override
	public VerificationToken generatedNewVerificationToken(String oldToken) {
		VerificationToken verificationToken = null;
		try {
		verificationToken = verificationTokenRepository.findByToken(oldToken);
		verificationToken.setToken(UUID.randomUUID().toString());
		verificationTokenRepository.save(verificationToken);
		}catch (Exception e) {
			log.error("UserServiceImpl::generatedNewVerificationToken()",e.toString());
		}
		return verificationToken;
	}


	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		try {
		PasswordResetToken passwordResetToken = new PasswordResetToken(user,token);
		passwordResetTokenRepository.save(passwordResetToken);
		}catch (Exception e) {
			log.error("UserServiceImpl::createPasswordResetTokenForUser()",e.toString());
		}
	}


	@Override
	public User findUserByEmail(String email) {
		User user = null ;
		try {
			user = userRepository.findByEmail(email);
		}catch(Exception e) {
			log.error("UserServiceImpl::findUserByEmail()",e.toString());
			return null;
		}
		return user;
	}
	
	

	


	@Override
	public String validatePasswordResetToken(String token) {
		PasswordResetToken passwordResetToken = null;
		try {
			passwordResetToken =passwordResetTokenRepository.findByToken(token);
			
			if(passwordResetToken == null) {
				return "Invalid Token";
			}
			
			//User user = passwordResetToken.getUser();
			Calendar cal = Calendar.getInstance();
			
			if((passwordResetToken.getExpirationTime().getTime() - cal.getTime().getTime()) <=0) {
				passwordResetTokenRepository.delete(passwordResetToken);
				return "Token Expired !";
			}
		}
		catch (Exception e) {
			log.error("UserServiceImpl::validatePasswordResetToken()",e.toString());
			return "Error occured";
		}
		
		return "Valid";
	}


	@Override
	public Optional<User> getUserByPasswordResetToken(String token) {
		Optional<User> user = null;
		try {
			user  = Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
		}catch (Exception e) {
			log.error("UserServiceImpl::getUserByPasswordResetToken()",e.toString());
			return null;
		}
		return user;
	}


	@Override
	public void changePassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		try {
			userRepository.save(user);
		}
		catch(Exception e) {
			log.error("UserServiceImpl::changePassword()",e.toString());
		}
	}


	@Override
	public boolean checkIfValidOldPassword(User user, String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}
	
	
	

}
