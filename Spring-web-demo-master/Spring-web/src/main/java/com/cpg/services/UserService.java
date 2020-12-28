package com.cpg.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpg.models.User;
import com.cpg.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User updateUser(int userId, User user) {
		Optional<User> user1=userRepository.findById(userId);
		if(user1.isEmpty()) {
			return null;
		}
		else {
			User user2=user1.get();
			BeanUtils.copyProperties(user, user2, "userId","approve");
			return userRepository.save(user2);
		}
		
	}

	public User approve(int userId, User user) {
		// TODO Auto-generated method stub
		Optional<User> user1=userRepository.findById(userId);
		if(user1.isEmpty()) {
			return null;
		}
		else {
			User user2=user1.get();
			BeanUtils.copyProperties(user, user2, "userId", "name","age","gender","password","email","contactNumber","address","role");
			return userRepository.save(user2);
		}
		
	}

}
