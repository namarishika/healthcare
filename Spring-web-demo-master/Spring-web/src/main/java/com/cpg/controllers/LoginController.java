package com.cpg.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpg.exceptions.UserNotFoundException;
import com.cpg.models.Login;
import com.cpg.services.LoginService;

@RestController
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	   
	
	@Autowired
	LoginService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Login login) {
		String message=service.signIn(login);
		if(message.equals("success")) {
			logger.info("succesfully signed in!!");
			return new ResponseEntity<>( "succesfully signed in!!", HttpStatus.OK);
		}
		
		logger.trace("login method accessed");
		throw new UserNotFoundException(message);
		
	}
	
	@DeleteMapping("/login/{id}")
	public ResponseEntity<String> logOut(@PathVariable int id) {
		String message=service.signOut(id);
		if(message.equals("success")) {
			logger.info("succesfully signed out!!");
			return new ResponseEntity<>("successfully signed out!!", HttpStatus.OK);
		}
		logger.trace("logout method accessed");
		throw new UserNotFoundException(message);
	}
	
	@PutMapping("/login/{id}")
	public ResponseEntity<Login> updatePassword(@PathVariable int id,@RequestParam("newpassword") String newPassword) {
		Login login=service.changePassword(id, newPassword);
		if(login==null) {
			logger.error("updatePassword method accessed");
			throw new UserNotFoundException("Please login to change the password");
		}
		logger.info("Password Updated!!");
		return new ResponseEntity<>( login, HttpStatus.OK);
	}
	
	

}
