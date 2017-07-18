package com.app.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.security.MyUser;
import com.app.service.UserRepository;

@Controller
public class MyProfileController {
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value = {"/myProfile"}, method = RequestMethod.GET)
	public String getMyProfilePage(Principal principal,Model model){
		model.addAttribute("user", userRepo.findOne(principal.getName()));
		return "myProfile";
	}
	
	@RequestMapping(value = {"/myProfile"}, method = RequestMethod.POST)
	public String updateMyProfile(@Valid @ModelAttribute MyUser user,BindingResult result, ModelMap model ,  HttpServletRequest request){
		model.addAttribute("user",user);
		if (result.hasErrors()) 
		    return "redirect:/myProfile";
		MyUser dbUser = userRepo.findOne(user.getUsername());
		dbUser.setEmail(user.getEmail());
		dbUser.setPhone(user.getPhone());
		dbUser.setPassword(user.getPassword());
		userRepo.save(dbUser);
		return "redirect:/myProfile";
	}
}
