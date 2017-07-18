package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.security.MyUser;
import com.app.service.AuthenticateUserAndSetSessionService;
import com.app.service.UserRepository;

@Controller
public class ProcessSocialSignInController {
	@Autowired
	Facebook facebook;
	@Autowired
	Google google;
	@Autowired
	LinkedIn linkedIn;
	@Autowired
	private UserRepository userService;
	@Autowired
	private AuthenticateUserAndSetSessionService authService;
	//@Autowired
    //private AuthenticationManager authenticationManager;
	
	//Map provider data received from SocialSignInController
	@RequestMapping(value = {"process/{provider}"}, method = RequestMethod.GET)
	public String signUpPage(@PathVariable("provider") String provider ,Model model, HttpServletRequest request){	
		String username = null,password = null,phone = null,email = null,displayName = null;
		//System.out.println("Got provider is : " + provider);
		//get data received from oath provider
		switch(provider){
		case "facebook":{
			username = facebook.userOperations().getUserProfile().getId();
			password = "temp";
			phone = "None";
			email = facebook.userOperations().getUserProfile().getEmail();
			displayName = facebook.userOperations().getUserProfile().getName();
		}
			break;
		case "google":{
			username = google.userOperations().getUserInfo().getId();
			password = "temp";
			phone = "None";
			email = google.userOperations().getUserInfo().getEmail();
			displayName = google.userOperations().getUserInfo().getName();

		}
			break;
		case "linkedin":{
			username = linkedIn.profileOperations().getUserProfile().getId();
			password = "temp";
			phone = "None";
			email = linkedIn.profileOperations().getUserProfile().getEmailAddress();
			displayName = linkedIn.profileOperations().getUserProfile().getFirstName();
		}
			break;
		}
		
		//update or create new user
		MyUser newUser;
		newUser = (MyUser) userService.loadUserByUsername(username);
		if (newUser==null){
			System.out.println("User created");
			newUser = new MyUser();
			newUser.setUsername(username);
			newUser.setDisplayName(displayName);
			newUser.setEmail(email);
			newUser.setPassword(password);
			newUser.setPhone(phone);
			newUser.setProvider(provider);
			newUser.addAuthority("ROLE_USER");
		}else{
			System.out.println("User updated");
			newUser.setDisplayName(displayName);
			newUser.setEmail(email);
			newUser.setPassword(password);
			newUser.setPhone(phone);
		}
		//System.out.println("saving social user");
		userService.save(newUser);
		//System.out.println("Authonitation social user");
		authService.authenticateUserAndSetSession(newUser, request);
		//authenticateUserAndSetSession(newUser, request);
		return "redirect:/products";
	}
	/*
	public void authenticateUserAndSetSession(MyUser user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();
        
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }*/
}
