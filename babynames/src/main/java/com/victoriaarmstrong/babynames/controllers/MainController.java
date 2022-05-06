package com.victoriaarmstrong.babynames.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.victoriaarmstrong.babynames.models.BabyName;
import com.victoriaarmstrong.babynames.models.User;
import com.victoriaarmstrong.babynames.services.MainService;
import com.victoriaarmstrong.babynames.services.UserService;

@Controller
public class MainController {
	
	private String HOMEPAGE = "redirect:/";
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private UserService userService;	
	
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		if(isNotLoggedIn(session)) {
			return HOMEPAGE;
		}

		// get the userId from session
		Long userId = (Long) session.getAttribute("userId");
		// use that userId to find the user
		User user = userService.findOneUser(userId);
		// add to model
		model.addAttribute("user", user);
		model.addAttribute("babyNames", mainService.allBabyNames());
		return "allNames.jsp";
	}
	
	@GetMapping("/names/new")
	public String newBabyNameForm(@ModelAttribute("newBabyName")BabyName babyName, HttpSession session) {
		if(isNotLoggedIn(session)) {
			return HOMEPAGE;
		}
		return "addName.jsp";
	}
	
	@PostMapping("/names/new")
	public String processNewBabyName(@Valid @ModelAttribute("newBabyName") BabyName babyName,
			BindingResult result, HttpSession session
			) {
		if(isNotLoggedIn(session)) {
			return HOMEPAGE;
		}
		if(result.hasErrors()) {
			return "addName.jsp";
		}else {
			mainService.createBabyName(babyName);
			return "redirect:/home";
		}	
	}
	
	
	
	@GetMapping("/names/{id}")
	public String oneBabyName(@PathVariable("id")Long id, Model model, HttpSession session) {
		if(isNotLoggedIn(session)) {
			return HOMEPAGE;
		}
		User user = userService.findOneUser(Long.parseLong(session.getAttribute("userId").toString()));
		// add to model
		model.addAttribute("newBabyName", mainService.findOneBabyName(id));
		model.addAttribute("liked", mainService.findOneBabyName(id).getLikedUsers().contains(user));

		return "oneName.jsp";
	}
	
	@GetMapping("/names/{id}/edit")
	public String editBabyNameForm(Model model, @PathVariable("id")Long id, HttpSession session) {
		model.addAttribute("newBabyName", mainService.findOneBabyName(id));
		if(isNotLoggedIn(session)) {
			return HOMEPAGE;
		}
		return "editName.jsp";
	}
	
	
	@PutMapping("/names/{id}/edit")
	public String processEditBabyName(@Valid @ModelAttribute("newBabyName") BabyName babyName,
			BindingResult result, HttpSession session) {
		
		if(isNotLoggedIn(session)) {
			return HOMEPAGE;
		}
		if(result.hasErrors()) {
			return "editName.jsp";
		}else {
			mainService.updateBabyName(babyName);
			return "redirect:/home";
		}
	}
	
	@DeleteMapping("/names/{id}/delete")
	public String deleteBabyName( @PathVariable("id")Long id, HttpSession session) {
		if(isNotLoggedIn(session)) {
			return HOMEPAGE;
		}
		mainService.deleteBabyName(id);
		return "redirect:/home";
		} 
	
	@PutMapping("/names/{id}/like")
	public String likeBabyName(@PathVariable("id")Long babyNameId, Model model,  HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");

		mainService.likeBabyName(babyNameId, userId);
		
		return "redirect:/home";
	}
	
	@PutMapping("/names/{id}/unlike")
	public String unlikeBabyName(@PathVariable("id")Long babyNameId, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		mainService.unlikeBabyName(babyNameId, userId);
		
		return "redirect:/home";
	}
	

	private boolean isNotLoggedIn(HttpSession session) {
		return (session.getAttribute("userId")==null);
	}
		
		
	}



 