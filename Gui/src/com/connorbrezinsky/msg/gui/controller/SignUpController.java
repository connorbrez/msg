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

import java.net.URL;
import java.util.ResourceBundle;

import com.connorbrezinsky.msg.user.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable {

	@FXML
	private TextField email;

	@FXML
	private TextField password, confirmPassword;

	public void initialize(URL location, ResourceBundle resources) {

	}

	public void btnSignUpClicked() throws Exception {

		String userEmail = email.getText();
		String userPassword = password.getText(), userPasswordConfirm = confirmPassword.getText();

		// if no email, leaves method;
		if (userEmail == null || userEmail.equals("")) {
			System.out.println("enter email");
			return;
		}

		// if no password, leaves method;
		if (userPassword == null || userPassword.equals("")) {
			System.out.println("enter password");
			return;
		}

		// continues along if both passwords match;
		if (userPassword.equals(userPasswordConfirm)) {

			@SuppressWarnings("unused")
			User user = new User(userEmail);

		} else {
			System.out.println("passwords do not match");
			return;
		}
	}

	public void gotoLogin() {

	}

}
