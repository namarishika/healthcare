package com.cpg.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpg.models.Login;
import com.cpg.models.User;
import com.cpg.repository.LoginRepository;
import com.cpg.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public String signIn(Login login) {
		Optional<Login> login1=loginRepository.findById(login.getUserId());
		if(login1.isEmpty()) {
		Optional<User> user=userRepository.findById(login.getUserId());
		if(user.isEmpty()) {
			return "invalid id";
		}
		else {
			User user1=user.get();
			if(user1.isApprove()) {
			if(user1.getPassword().equals(login.getPassword())) {
			loginRepository.save(login);
			return "success";
			}
			else
				return "invalid password";
			}
			else
				return "User not approved";
		}
		}
		else
			return "User already logged in";
		
		
	}
	

	@Transactional
	public String signOut(int id) {
		Optional<Login> login=loginRepository.findById(id);
		if(login.isEmpty()) {
			return "Please login";
		}
		loginRepository.deleteById(id);
		return "success";
		
	}
	
	@Transactional
	public Login changePassword(int id, String newpass) {
		Optional<Login> login1=loginRepository.findById(id);
		if(login1.isPresent()) {
		Login login=login1.get();
			User user=userRepository.getOne(id);
			login.setPassword(newpass);
			user.setPassword(newpass);
			return loginRepository.save(login);
		}
		return null;
		
	}
	

}
