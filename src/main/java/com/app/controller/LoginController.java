package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.app.security.Authorities;
import com.app.security.MyUser;
import com.app.service.AuthenticateUserAndSetSessionService;
import com.app.service.AuthoritiesRepository;
import com.app.service.UserRepository;

@Controller
public class LoginController {
	@Autowired
	private UserRepository userService;
	//@Autowired
    //private AuthenticationManager authenticationManager;
	@Autowired
	private AuthenticateUserAndSetSessionService authService;
	
	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public String loginPage(){
		//System.out.println("Login controller executed");
		//check if user already connected - redirect to home page if true
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			//handle cases with wrong authorisation
			return "redirect:/products";
		} else {
			return "login";
		}
	}
	@RequestMapping(value = {"/signUp"}, method = RequestMethod.GET)
	public String signUpPage(Model model){
		//redirect user to home page if already connected
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails)
			return "redirect:/products";
		//load new user instance - form object
		model.addAttribute("user",new MyUser());
		return "signUp";
	}
	@RequestMapping(value = {"/signUp"}, method = RequestMethod.POST)
	public String submitSignUp(@Valid @ModelAttribute MyUser user,BindingResult result, ModelMap model ,  HttpServletRequest request){
		//System.out.println("post signUp controller executed");
		model.addAttribute("user",user);
		if (result.hasErrors()) {
		    //System.out.println(" * * * * Has Errors");
		    return "signUp";
		} else {
			//sign a new user - add ROLE_USER
			//System.out.println(" * * * * All Good");
			user.addAuthority("ROLE_USER");
			user.setDisplayName(user.getUsername());
			user.setProvider("local");
			userService.save(user);
			//authenticateUserAndSetSession(user, request);
	        authService.authenticateUserAndSetSession(user, request);
			return "redirect:/products";
		}
	}
	@RequestMapping("/home")
	public String homePage(){
		//System.out.println("home controller executed");
		return "home";
	}
	
	
	
}
