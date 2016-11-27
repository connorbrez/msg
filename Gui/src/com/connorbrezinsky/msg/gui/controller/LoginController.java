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

package com.connorbrezinsky.msg.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.connorbrezinsky.msg.gui.main.Main;
import com.connorbrezinsky.msg.service.LoginService;
import com.connorbrezinsky.msg.service.UserExistsService;
import com.connorbrezinsky.msg.user.User;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

	@FXML
	private TextField inptEmail;
	@FXML
	private TextField inptPassword;
	@FXML
	private ProgressIndicator loading;

	public void initialize(URL location, ResourceBundle resources) {
		loading.setVisible(false);
	}

	public void btnLoginClicked() throws Exception {
		loading.setVisible(true);

		String email = inptEmail.getText();
		final String password = inptPassword.getText();

		if (email == null || email.equals("")) {
			System.err.println("enter email");
			loading.setVisible(false);

			return;
		}

		if (password == null || password.equals("")) {
			System.err.println("enter password");
			loading.setVisible(false);

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
								loading.setVisible(false);

								Parent root = null;
								try {
									Main.user = userExists.getUser();
									root = FXMLLoader.load(Main.class.getResource("DashView.fxml"));
								} catch (IOException e) {
									e.printStackTrace();
								}

								if (root != null) {
									Main.stage.setScene(new Scene(root, 600, 400));
								} else {
									System.exit(1);
								}
							} else {
								System.err.println("could not login user");
								loading.setVisible(false);

							}
						}

					});

					loginService.setOnFailed(new EventHandler<WorkerStateEvent>() {
						public void handle(WorkerStateEvent event) {
							System.err.println("login service failed");
							loading.setVisible(false);

						}
					});

					loginService.start();
					userExists.setUser(loginService.getUser());
				} else {
					loading.setVisible(false);

				}
			}

		});
		userExists.start();
	}

}
