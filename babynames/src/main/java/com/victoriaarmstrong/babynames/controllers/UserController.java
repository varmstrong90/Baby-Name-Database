package com.victoriaarmstrong.babynames.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.victoriaarmstrong.babynames.models.LoggedInUser;
import com.victoriaarmstrong.babynames.models.User;
import com.victoriaarmstrong.babynames.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
		//creates an empty User object for registration
		model.addAttribute("newUser", new User());
		//created a new LoggedInUser object for logging in
		model.addAttribute("newLogin", new LoggedInUser());
		
		return "index.jsp";
	}
	
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
    	userService.register(newUser, result);
        
        if(result.hasErrors()) {
        	// check validation
            model.addAttribute("newLogin", new LoggedInUser()); // login
            return "index.jsp";
        }
        
        // No errors, store the id in session
        session.setAttribute("userId", newUser.getId());
        session.setAttribute("name", newUser.getName());
    
        return "redirect:/home";
    }
    
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoggedInUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
         User user = userService.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
    
        // No errors, store the id in session
        session.setAttribute("userId", user.getId());
        session.setAttribute("name", user.getName());
    
        return "redirect:/home";
    } 
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate(); 
    	return "redirect:/";
    }
    
}
