package com.codingdojo.muse.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.muse.models.LoginUser;
import com.codingdojo.muse.models.User;
import com.codingdojo.muse.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public User validateReg(User userRegData, BindingResult bindingResult) {
		if (!userRegData.getPassword().equals(userRegData.getConfirm())) {
			bindingResult.rejectValue("password","passwordsDontMatch.newUser.password", "Passwords don't match!");
		}
		Optional<User> potentialUser = userRepo.findByEmail(userRegData.getEmail());
		if (potentialUser.isPresent()) {
			bindingResult.rejectValue("email", "emailExists.newUser.email", "An account already exists. Please try again.");
		}
		if (!bindingResult.hasErrors()) {
			String hashedPassword = BCrypt.hashpw(userRegData.getPassword(), BCrypt.gensalt());
			userRegData.setPassword(hashedPassword);
			return userRepo.save(userRegData);
		} else {
		return null;
		}
	}

	public User validateLogin(LoginUser loginData, BindingResult bindingResult) {
		Optional<User> potentialUser = userRepo.findByEmail(loginData.getLoginEmail());
		if (potentialUser.isEmpty()) {
			bindingResult.rejectValue("loginEmail", "invalidLogin.loginUser.loginEmail", "Invalid login.");
			return null;
		}
		User foundUser = potentialUser.get();
		if(!BCrypt.checkpw(loginData.getLoginPassword(), foundUser.getPassword())) {
			bindingResult.rejectValue("loginPassword", "invalidLogin.loginUser.loginPassword", "Invalid login.");
			return null;
		}
		if(!bindingResult.hasErrors()) {
			return foundUser;
		} else {
			return null;
		}
	}
	
	public User readUserById(Long id) {
		Optional<User> existingUser = userRepo.findById(id);
		return existingUser.isPresent() ? existingUser.get() : null;
	}
}
