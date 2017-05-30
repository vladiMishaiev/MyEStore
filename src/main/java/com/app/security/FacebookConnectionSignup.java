package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.app.service.UserRepository;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
	 @Autowired
	 private UserRepository userService;

	    @Override
	    public String execute(Connection<?> connection) {
	        System.out.println("signup === ");
	        final Users user = new Users();
	        user.setUsername(connection.getDisplayName());
	        user.setPassword(/*randomAlphabetic(8)*/"random");
	        userService.save(user);
	        return user.getUsername();
	    }

}
