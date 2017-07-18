package com.app.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.service.TransactionRepository;

@Controller
public class TransactionController {
	@Autowired
	private TransactionRepository transactionRepo;
	
	@RequestMapping(value = {"/transactions"}, method = RequestMethod.GET)
	public String getMyProfilePage(Principal principal,Model model,HttpServletRequest request){
		//model.addAttribute("transactions", transactionRepo.findAll());
		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addAttribute("transactions", transactionRepo.findAll());
	    }else {
	    	model.addAttribute("transactions", transactionRepo.findByUserName(principal.getName()));
	    }
		return "/transactions";
	}
	

}
