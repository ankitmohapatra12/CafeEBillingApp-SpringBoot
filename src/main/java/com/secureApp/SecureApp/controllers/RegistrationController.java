package com.secureApp.SecureApp.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.secureApp.SecureApp.Event.RegistrationCompleteEvent;
import com.secureApp.SecureApp.Model.PasswordModel;
import com.secureApp.SecureApp.Model.UserModel;
import com.secureApp.SecureApp.entity.CustomerRegistration;
import com.secureApp.SecureApp.entity.User;
import com.secureApp.SecureApp.entity.VerificationToken;
import com.secureApp.SecureApp.service.CustomerRegistrationService;
import com.secureApp.SecureApp.service.UserService;


/**
 * @author Ankit Mohapatra
 * 
 */


@RestController
public class RegistrationController {

	@Autowired
	private CustomerRegistrationService customerRegistrationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping(value="/CustomerRegister",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String customerRegistration(@RequestBody CustomerRegistration customer) throws Exception{
		 String response = customerRegistrationService.saveCustomerDetails(customer);
		 return response;
	}
	
	
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserModel userModel,final HttpServletRequest request) {
		//save method
		User user = userService.registerUser(userModel);
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
		return "Success";
	}
	
	
	private String applicationUrl(HttpServletRequest request) {
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
	
	
	@GetMapping("/verifyRegistration")
	public String verifyRegistration(@RequestParam("token") String token) {
		String result = userService.validateVerificationToken(token);
		if(result.equalsIgnoreCase("valid")) {
			return "User verified Successfully !";
		}
		return "Failed to verify User";
	}
	
	
	@GetMapping("/resendVerifyToken")
	public String resendVerification(@RequestParam("token") String oldToken, HttpServletRequest request) {
		VerificationToken verificationToken = userService.generatedNewVerificationToken(oldToken);
		
		User user = verificationToken.getUser();
		resentVerificationTokenMail(user,applicationUrl(request),verificationToken);
		return "Verification link sent !";
	}
	
	
	
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestBody PasswordModel passwordModel,HttpServletRequest request) {
		User user = userService.findUserByEmail(passwordModel.getEmail());
		String url ="";
		if(user!=null) {
			String token = UUID.randomUUID().toString();
			userService.createPasswordResetTokenForUser(user,token);
			url = passwordResetTokenMail(user,applicationUrl(request),token);
		}
		else {
			return "Email not found !";
		}
		
		return url;
	}
	
	
	@PostMapping("/savePassword")
	public String savePassword(@RequestParam("token") String token, @RequestBody PasswordModel passwordModel) {
		
		String result = userService.validatePasswordResetToken(token);
		
		if(!result.equalsIgnoreCase("valid")) {
			return "Invalid Token";
			
		}
		
		Optional<User> user = userService.getUserByPasswordResetToken(token);
		
		if(user.isPresent()) {
			userService.changePassword(user.get(),passwordModel.getNewPassword());
			return "Password Reset Successfully";
		}
		
		else {
			return "Invalid token !";
		}
	}
	
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestBody PasswordModel passwordModel) {
		User user = userService.findUserByEmail(passwordModel.getEmail());
		String res ="";
		if(user!=null) {
			if(!user.isEnabled()) {
				res = "User is not registered !";
				
			}
			if(!userService.checkIfValidOldPassword(user,passwordModel.getOldPassword())) {
				res = "Invalid Old Password !";
			}
			else {
				//save new password functionality...
				userService.changePassword(user,passwordModel.getNewPassword());
				res="Password changed successfully !";
			}
			
		}
		
		else {
			res="Cant find a user with this email !";
			return res;
		}
		return res;
	}
	


	private String passwordResetTokenMail(User user, String applicationUrl, String token) {
		String url = applicationUrl + "/savePassword?token="+token;
		return url;
	}


	private void resentVerificationTokenMail(User user, String applicationUrl ,VerificationToken verificationToken) {
		String url = applicationUrl + "/verifyRegistration?token="+verificationToken.getToken();
		
		
		//send email to user here
		
		System.out.println("Click to verify your account --> ");
		System.out.println(url);
	}
	
}
