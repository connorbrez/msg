package com.connorbrezinsky.msg.user;

public class User {

	private boolean verified, exists;
	private String email;
	
	public User(String email){
		this.setEmail(email);
	}
	
	public User(){
		
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean exists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}
	
	
	
}
