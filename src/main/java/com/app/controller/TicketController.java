package com.app.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.app.domain.Ticket;
import com.app.security.MyUser;
import com.app.service.TicketsRepository;

@Controller
public class TicketController {
	@Autowired
	TicketsRepository ticketRepo;
	
	@RequestMapping(value = {"/tickets"}, method = RequestMethod.GET)
	public String getTickets(Model model){
		model.addAttribute("tickets",ticketRepo.findAll());
		return "tickets";
	}
	
	@RequestMapping(value = {"/openTicket"}, method = RequestMethod.POST)
	public String openTicket(@Valid @ModelAttribute Ticket ticket, @Valid @ModelAttribute MyUser user,BindingResult result,Model model,HttpServletRequest request){
		ticket.setDate(new Date());
		ticketRepo.save(ticket);
		model.addAttribute("resMsg","Ticket was submitted");
		model.addAttribute("user",user);
		model.addAttribute("ticket",ticket);
		//return "redirect:/products";
		return "contactUs";
	}
	
}
