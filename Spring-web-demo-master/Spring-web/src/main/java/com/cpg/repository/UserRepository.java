package com.cpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpg.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public void deleteByUserId(int userId);

}
