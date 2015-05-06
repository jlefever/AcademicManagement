package edu.ycp.cs320.acadman.model;

public class User {
	private String username;
	private String email;
	private String password;
	private int permissions;

	public User() {

	}

	public User(String username, String email, String password, int permissions){
		this.username = username;
		this.email = email;
		this.password = password;
		this.permissions = permissions;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPermissions() {
		return permissions;
	}
	
	public void setPermissions(int permissions){
		this.permissions = permissions;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (permissions != other.permissions)
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email
				+ ", password=" + password + ", permissions=" + permissions + "]";
	}

}