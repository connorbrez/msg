/*   msg. Encrypted Message. For everyone.
    Copyright (C) 2016 Connor Brezinsky

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

package com.connorbrezinsky.msg.client.main;

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
