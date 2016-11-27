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

package com.connorbrezinsky.msg.gui.main;

import com.connorbrezinsky.msg.user.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static final int MAX_MESSAGE_SIZE = 500;
	
	public static User user;
	
	public static Stage stage;
	

	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	@Override
	public void start(Stage s) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("DashView.fxml"));
		stage = s;
		stage.setTitle("msg");
		stage.setScene(new Scene(root, 600, 400));
		stage.setResizable(false);
		stage.show();

	}

}
