package com.secureApp.SecureApp.Event;

import org.springframework.context.ApplicationEvent;

import com.secureApp.SecureApp.entity.User;


/**
 * @author Ankit Mohapatra
 * 
 */


public class RegistrationCompleteEvent extends ApplicationEvent {
	

	private static final long serialVersionUID = 1L;
	private User user;
	private String applicationUrl;
	
	
	




	public RegistrationCompleteEvent(User user, String applicationUrl) {
		super(user);
		this.user = user;
		this.applicationUrl = applicationUrl;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getApplicationUrl() {
		return applicationUrl;
	}



	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
	
	
}
