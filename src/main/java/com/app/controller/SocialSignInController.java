package com.app.controller;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.google.api.Google;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/connect")
public class SocialSignInController extends ConnectController {
	/*
	 * Intercept data from provider
	 * */
	public SocialSignInController(ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}
	@Override
	protected String connectedView(String providerId) {
		//redirect data to handle save process
		//System.out.println("* * * * Got to social controller - redirect from provider");
		//return "redirect:/"+providerId+"/authenticate";
		return "redirect:/process/"+providerId;
	}
}
