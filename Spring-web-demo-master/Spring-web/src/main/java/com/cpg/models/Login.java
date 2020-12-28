package com.cpg.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name="Login")
@Table
public class Login {
	@Id
	@Column(name="user_id")
	private int userId;
	
	@NotNull
	@Column(name = "password")
	private String password;
   
	// constructor
	public Login() {

	}

	public Login(int userId, String password) {
		super();
		this.userId=userId;
		this.password = password;
		
	}

	// getters and setters
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + "]";
	}

}
