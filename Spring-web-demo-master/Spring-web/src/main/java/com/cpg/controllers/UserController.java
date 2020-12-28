package com.cpg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cpg.exceptions.UserNotFoundException;
import com.cpg.models.User;
import com.cpg.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user) {
		User user1 = userService.updateUser(id, user);
		if(user1 == null) {
			throw new UserNotFoundException("Invalid user id");
		}
		else {
			return new ResponseEntity<>(user1,HttpStatus.OK);
		}
	}
	
	@PatchMapping("/user/{id}")
	public ResponseEntity<User> approve(@PathVariable int id,@RequestBody User user) {
		User user1 = userService.approve(id,user);
		if(user1 == null) {
			throw new UserNotFoundException("Invalid user id");
		}
		else {
			return new ResponseEntity<>(user1,HttpStatus.OK);
		}
	}

}
