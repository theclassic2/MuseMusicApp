package com.codingdojo.muse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.muse.models.LoginUser;
import com.codingdojo.muse.models.User;
import com.codingdojo.muse.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private UserService userServ;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/")
	public String index(@ModelAttribute("newUser") User newUser, @ModelAttribute("loginUser") LoginUser loginUser) {
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String registrationHandler(@Valid @ModelAttribute("newUser") User newUser, BindingResult result,
			@ModelAttribute("loginUser") LoginUser loginUser) {
		User registerUser = userServ.validateReg(newUser, result);
		if (registerUser == null) {
			return "index.jsp";
		}
		session.setAttribute("userID", registerUser.getId());
		return "redirect:/songs";
	}
	
	@PostMapping("/login")
	public String loginHandler(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result,
			@ModelAttribute("newUser") User newUser) {
		User currentUser = userServ.validateLogin(loginUser, result);
		if (currentUser == null) {
			return "index.jsp";
		}
		session.setAttribute("userID", currentUser.getId());
		return "redirect:/songs";
	}
	
	@PostMapping("/logout")
	public String logoutUser() {
		session.invalidate();
		return "redirect:/";
	}
}
