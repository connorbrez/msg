package com.connorbrezinsky.msg.server.main;

public class User {

	private String username;
	private String client_id;

	public User setUsername(String m) {
		this.username = m;
		return this;
	}

	public User setClientId(String id) {
		this.client_id = id;
		return this;
	}

	public String getUsername() {
		return this.username;
	}

	public String getClientId() {
		return this.client_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client_id == null) ? 0 : client_id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
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
		if (client_id == null) {
			if (other.client_id != null)
				return false;
		} else if (!client_id.equals(other.client_id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
