package com.victoriaarmstrong.babynames.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.victoriaarmstrong.babynames.models.LoggedInUser;
import com.victoriaarmstrong.babynames.models.User;
import com.victoriaarmstrong.babynames.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	//registration logic
	public User register(User newUser, BindingResult result) {
		//Get Email address and make sure it isn't already in the database
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		
		
		//if Email does exist, reject the submission with an error message
		if(potentialUser.isPresent()) {
			result.rejectValue("email", "unique", "This E-mail address is already registered");
		}
	
		//check that the given passwords match each other. If not, reject with error message
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "matches", "The password and confirm password do not match. Please try again.");
		}

		//if there are any errors, exit the method without saving to the database
		if(result.hasErrors()) {
			return null;
		}
		
		//Hash the password and store it to the database
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepo.save(newUser);
		}
	
	//logging in logic
	public User login(LoggedInUser newLogin, BindingResult result) {
		
		//find user in the database by Email
		Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
		
		//If it does not exist, reject with an error message
		if(!potentialUser.isPresent()) {
			result.rejectValue("email", null, "We can not find any user associated with the E-mail address you provided. Please try again." );
			return null;
		}
		
		//get user
		User user = potentialUser.get();
		
		//use Bcrypt to check that the passwords match. If they do not, reject with an error message
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "matches", "Your password doesn't match what we have in our records. Please try again.");
		}
		
		//if there are errors, return null, else return user
		if(result.hasErrors()) {
			return null;
		}
		return user;
	}
	
	public User findOneUser(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}else {
			return null;
		}
	}

}
