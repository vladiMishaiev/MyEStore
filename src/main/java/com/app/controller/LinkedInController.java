package com.app.controller;

import java.util.List;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.ProfileUrl;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.ProfileOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/linkedin")
public class LinkedInController {

    private LinkedIn linkedIn;
    private ConnectionRepository connectionRepository;

    public LinkedInController(LinkedIn linkedIn, ConnectionRepository connectionRepository) {
        this.linkedIn = linkedIn;
   
        this.connectionRepository = connectionRepository;
    }
    
    @RequestMapping(value = {"/authenticate"}, method = RequestMethod.GET)
	public String signUpPage(Model model){
    	//ProfileOperations user = linkedIn.profileOperations();
    	LinkedInProfile user = linkedIn.profileOperations().getUserProfile();
    	System.out.println(user.getEmailAddress());
		
		
        return "redirect:/home";
	}
}