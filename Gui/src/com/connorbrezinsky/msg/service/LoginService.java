/*    msg. Encrypted Messaging. For everyone.
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

package com.connorbrezinsky.msg.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.connorbrezinsky.msg.database.Database;
import com.connorbrezinsky.msg.security.Hash;
import com.connorbrezinsky.msg.user.User;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class LoginService extends Service<Boolean> {

	private Connection connection;
	private Statement statement;
	private User user;
	private String password;

	@Override
	protected Task<Boolean> createTask() {
		return new Task<Boolean>() {
			@Override
			protected Boolean call() throws Exception {
				if (password == null || password.equals("")) {
					System.err.println("Enter password for login service");
					return false;
				}

				// ssl disabled for development
				connection = DriverManager.getConnection("jdbc:mysql://" + Database.HOST + "/" + Database.DATABASE
						+ "?useSSL=false&user=" + Database.USERNAME + "&password=" + Database.PASSWORD);

				statement = connection.createStatement();

				ResultSet query = statement
						.executeQuery("SELECT password FROM users WHERE email='" + user.getEmail() + "';");
				query.next();

				String p = query.getString("password");

				try {
					query.close();
				} catch (Exception e) {
				} finally {
					query = null;
				}
				if (Hash.check(password, p)) {
					user.setVerified(true);

					try {
						statement.close();
						connection.close();
					} catch (Exception x) {
					} finally {
						statement = null;
						connection = null;
					}

					return true;
				}
				return false;
			}
		};
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
