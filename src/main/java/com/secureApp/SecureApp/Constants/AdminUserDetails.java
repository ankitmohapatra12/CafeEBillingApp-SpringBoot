package com.secureApp.SecureApp.Constants;

import java.util.ArrayList;
import java.util.List;

import com.secureApp.SecureApp.entity.User;
/**
 * @author Ankit Mohapatra
 * 
 */
public class AdminUserDetails {

	
	public static List<User> adminUsers = new ArrayList<>();

	public static List<User> getAdminUsers() {
		return adminUsers;
	}

	public static void setAdminUsers(List<User> adminUsers) {
		AdminUserDetails.adminUsers = adminUsers;
	}
}
