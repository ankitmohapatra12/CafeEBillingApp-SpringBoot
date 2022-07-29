package com.secureApp.SecureApp.Listener;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.secureApp.SecureApp.Event.RegistrationCompleteEvent;
import com.secureApp.SecureApp.entity.User;
import com.secureApp.SecureApp.service.UserService;


/**
 * @author Ankit Mohapatra
 * 
 */


@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>{
	
	private static final Logger log = LoggerFactory.getLogger(RegistrationCompleteEventListener.class);
	
	@Autowired
	private UserService userService;
	
	
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		//creating the verification token for the user with link 
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		try {
		userService.saveVerificationTokenForUser(token,user);
		
		//Sending mail to User
		
		String url = event.getApplicationUrl() + "/verifyRegistration?token="+token;
		
		
		//send email to user here
		
		System.out.println("Click to verify your account --> ");
		System.out.println(url);
		}
		catch (Exception e) {
			log.error("RegistrationCompleteEventListener::onApplicationEvent()",e.toString());
		}
	    
		
	}
}
