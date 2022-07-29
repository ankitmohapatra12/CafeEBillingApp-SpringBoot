package com.secureApp.SecureApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.secureApp.SecureApp.Constants.AdminUserDetails;
import com.secureApp.SecureApp.entity.User;
import com.secureApp.SecureApp.repository.UserRepository;

@SpringBootApplication
public class SecAppApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SecAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		List<User> users = userRepo.findAll();
		AdminUserDetails.setAdminUsers(users);
	}

}
