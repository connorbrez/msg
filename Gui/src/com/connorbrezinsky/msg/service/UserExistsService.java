/*   msg. Encrypted Messaging. For everyone.
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
import com.connorbrezinsky.msg.user.User;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class UserExistsService extends Service<Boolean> {

	private User user;
	private Connection connection;
	private Statement statement;

	@Override
	protected Task<Boolean> createTask() {
		return new Task<Boolean>() {
			@Override
			protected Boolean call() throws Exception {

				//ssl disabled for development
				connection = DriverManager.getConnection("jdbc:mysql://" + Database.HOST + "/" + Database.DATABASE
						+ "?useSSL=false&user=" + Database.USERNAME + "&password=" + Database.PASSWORD);
				statement = connection.createStatement();

				ResultSet query = statement
						.executeQuery("SELECT email FROM users WHERE email='" + user.getEmail() + "';");
				query.next();

				String e = query.getString("email");

				try {
					query.close();
				} catch (Exception x) {
				} finally {
					query = null;
				}

				if (e == null || e.isEmpty()) {
					System.err.println("user doesn't exist");
					user.setVerified(false);
					user.setExists(false);

					try {
						statement.close();
						connection.close();

					} catch (Exception ex) {
					} finally {
						statement = null;
						connection = null;
					}

					return false;
				} else {
					System.out.println("user exists");
					System.out.println("here");
					user.setExists(true);
					user.setVerified(false);

					try {
						statement.close();
						connection.close();

					} catch (Exception ex) {
					} finally {
						statement = null;
						connection = null;
					}
					
					return true;
				}

			}
		};

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
