package com.connorbrezinsky.msg.server.main;

public class Message {

	private String message;
	private String client_id;
	private User user;

	public Message setMessage(String m) {
		this.message = m;
		return this;
	}

	public Message setClientId(String id) {
		this.client_id = id;
		return this;
	}

	public Message setUser(User user) {
		this.user = user;
		return this;
	}

	public User getUser() {
		return this.user;
	}

	public String getMessage() {
		return this.message;
	}

	public String getClientId() {
		return this.client_id;
	}

}
