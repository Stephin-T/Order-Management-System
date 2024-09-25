package com.training.task.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserDTO {
	
	 @NotBlank(message = "Username should not be empty.")
	    private String username;

	    @NotBlank(message = "Password should not be empty.")
	    private String password;

	    @NotNull(message = "Role should not be null.")
	    private Role role;
	    
	    
	    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public UserDTO(String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
	

}
