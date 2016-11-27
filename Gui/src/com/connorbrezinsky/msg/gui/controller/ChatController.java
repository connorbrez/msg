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

import com.connorbrezinsky.msg.gui.main.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChatController implements Initializable {


	@FXML
	private ScrollPane scrollPane;
	@FXML
	private VBox content;

	@FXML
	private TextField inptMessageBox;
	@FXML
	private Button btnSend, btnLeave, btnClientList, btnServerInfo, btnHome;



	public void initialize(URL location, ResourceBundle resources) {
		scrollPane.requestFocus();

		
	}

	int x = 0;

	public void btnSendClicked() {
		String message = inptMessageBox.getText();
		if (message == null || message.equals("") || message.length() > Main.MAX_MESSAGE_SIZE) {
			System.err.println("You must enter a message or your message is too long. (Max 500 characters)");
			return;
		}

		Label label = new Label("You: " + message);
		if (x == 0) {
			label.setStyle("-fx-background-color:#DCDCDC");
			x++;
		} else {
			label.setStyle("-fx-background-color:#E8E8E1");
			x--;
		}

		label.setPrefWidth(content.getPrefWidth());
		content.setPrefHeight(8.0);
		content.getChildren().add(label);

		inptMessageBox.clear();

		// TODO send the actual message;
	}

}
