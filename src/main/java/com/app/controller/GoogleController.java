package com.app.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PersonQueryBuilder;
import org.springframework.social.google.api.plus.ProfileUrl;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.security.MyUser;

@Controller
@RequestMapping("/google")
public class GoogleController {

	private Google google;
	private ConnectionRepository connectionRepository;

	public GoogleController(Google google, ConnectionRepository connectionRepository) {
		this.google = google;
		this.connectionRepository = connectionRepository;
	}
	@RequestMapping(value = {"/authenticate"}, method = RequestMethod.GET)
	public String signUpPage(Model model){
		Person user = google.plusOperations().getGoogleProfile();
		List<ProfileUrl> user2 = google.plusOperations().getPerson("me").getUrls();
		System.out.println(google.isAuthorized());
		GoogleUserInfo user3 = google.userOperations().getUserInfo();
		System.out.println("Email is : "+user3.getEmail());
		return "redirect:/home";
	}


}
