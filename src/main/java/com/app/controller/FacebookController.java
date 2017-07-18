package com.app.controller;

import org.springframework.boot.autoconfigure.social.FacebookProperties;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.ProfileOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/facebook")
public class FacebookController {

    private Facebook facebook;
	private ConnectionRepository connectionRepository;

    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }
    
    @RequestMapping(value = {"/authenticate"}, method = RequestMethod.GET)
	public String signUpPage(Model model){
    	//ProfileOperations user = linkedIn.profileOperations();
    	
    	//User user = facebook.userOperations().getUserProfile();
    	//LinkedInProfile user = facebook.profileOperations().getUserProfile();
    	//System.out.println(user.getEmail());
    	//String [] fields = { "id","name","birthday","email","location","hometown","gender","first_name","last_name"};
    	//User user = facebook.fetchObject("me", User.class, fields);
    	//String email=user.getEmail();
    	System.out.println(facebook.userOperations().getUserProfile().getFirstName());
    	System.out.println(facebook.userOperations().getUserProfile().getLastName());
    	System.out.println(facebook.userOperations().getUserProfile().getName());
    	System.out.println(facebook.userOperations().getUserProfile().getEmail());
    	
    	
    	return "redirect:/home";
	}
   
    public Facebook getFacebook() {
		return facebook;
	}
    
}