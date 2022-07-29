package com.secureApp.SecureApp.Model;


/**
 * @author Ankit Mohapatra
 * 
 */

public class PasswordModel {

	private String email;
	private String oldPassword;
	private String newPassword;
	
	
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
