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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.connorbrezinsky.msg.gui.main.Main;
import com.connorbrezinsky.msg.user.Server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class DashController implements Initializable {

	ArrayList<Server> servers = new ArrayList<>();

	@FXML
	private ScrollPane serverPane;

	@FXML
	private ImageView profilePicture;

	@FXML
	private Button btnAddServer, btnRemoveServer, btnSettings;

	// NewServer nodes
	@FXML
	private Button btnDone, btnCancel;

	@FXML
	private TextField inptServerIp, inptEncryptionKey;

	public void initialize(URL location, ResourceBundle resources) {
		if (profilePicture != null) {
			serverPane.requestFocus();
			Circle circle = new Circle();
			circle.setLayoutX(72);
			circle.setLayoutY(72);
			circle.setRadius(72);
			profilePicture.setClip(circle);
		}

	}

	public void btnAddServerClicked() {
		Parent root = null;

		try {
			root = FXMLLoader.load(Main.class.getResource("NewServer.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (root != null) {
			Main.stage.setScene(new Scene(root, 600, 400));
		} else {
			System.exit(1);
		}
	}

	public void btnRemoveServerClicked() {

	}

	public void btnQuickConnectClicked() {

	}

	public void btnSettingsClicked() {
	}

	public void btnDoneClicked() {

	}

	public void btnCancelClicked() {

		inptServerIp.clear();
		inptEncryptionKey.clear();

		Parent root = null;
		try {
			root = FXMLLoader.load(Main.class.getResource("DashView.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (root != null) {
			Main.stage.setScene(new Scene(root, 600, 400));
		} else {
			System.exit(1);
		}
	}

}
