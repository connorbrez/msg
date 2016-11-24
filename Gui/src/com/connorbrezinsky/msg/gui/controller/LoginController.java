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

 package com.connorbrezinsky.msg.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.connorbrezinsky.msg.service.LoginService;
import com.connorbrezinsky.msg.service.UserExistsService;
import com.connorbrezinsky.msg.user.User;

import javafx.concurrent.Worker.State;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

	@FXML
	private TextField inptEmail;
	@FXML
	private TextField inptPassword;

	public void initialize(URL location, ResourceBundle resources) {

	}

	public void btnLoginClicked() throws Exception {
		String email = inptEmail.getText();
		final String password = inptPassword.getText();

		if (email == null || email.equals("")) {
			System.err.println("enter email");
			return;
		}

		if (password == null || password.equals("")) {
			System.err.println("enter password");
			return;
		}

		User user = new User(email);

		final UserExistsService userExists = new UserExistsService();

		userExists.setUser(user);

		userExists.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			public void handle(WorkerStateEvent t) {
				final boolean b = userExists.getValue();
				userExists.getUser().setExists(b);

				if (b) {
					final LoginService loginService = new LoginService();
					loginService.setUser(userExists.getUser());
					loginService.setPassword(password);

					loginService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

						public void handle(WorkerStateEvent event) {
							final boolean b = userExists.getValue();
							userExists.getUser().setVerified(b);

							if (userExists.getUser().isVerified()) {
								System.out.println("successfully logged in");
							}else{
								System.err.println("could not login user");
							}
						}

					});

					loginService.setOnFailed(new EventHandler<WorkerStateEvent>() {
						public void handle(WorkerStateEvent event) {
							System.err.println("login service failed");
						}
					});

					loginService.start();
					userExists.setUser(loginService.getUser());
				}
			}

		});
		userExists.start();
		user = userExists.getUser();


	}

}
