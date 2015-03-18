package edu.ycp.cs320.acadman.model;

public class User {
	
	private String username;
	private String password;
	private Role myRole;
	
	public User(){
		
	}

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

	public Role getMyRole() {
		return myRole;
	}

	public void setMyRole(Role myRole) {
		this.myRole = myRole;
	}
}