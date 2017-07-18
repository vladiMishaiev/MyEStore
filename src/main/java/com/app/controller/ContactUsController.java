package com.app.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.domain.Ticket;
import com.app.security.MyUser;
import com.app.service.UserRepository;

@Controller
public class ContactUsController {
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value = {"/contactUs"}, method = RequestMethod.GET)
	public String getcontactUsPage(Principal principal,Model model,HttpServletRequest request){
		Ticket ticket = new Ticket();
		MyUser user = userRepo.findOne(principal.getName());
		
		ticket.setUsername(user.getUsername());
		model.addAttribute("resMsg","");
		model.addAttribute("user", user);
		model.addAttribute("ticket",ticket);
		return "contactUs";
	}
	
}
