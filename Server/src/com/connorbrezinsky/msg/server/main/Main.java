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

 package com.connorbrezinsky.msg.server.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.connorbrezinsky.msg.server.config.Config;
import com.connorbrezinsky.msg.server.config.ServerProperties;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class Main {

	public static final String VERSION = "0.1";
	
	public static Server server;
	static Kryo kryo;

	private static String save_dir = Config.HOME_DIR + File.separator + Config.SAVE_DIR + File.separator, PORT;
	static ServerProperties serverProperties;

	static ArrayList<User> users = new ArrayList<>();
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		// Config house keeping;
		if (new File(save_dir).mkdir()) {
			System.out.println("Save dir created");
		}
		serverProperties = new ServerProperties();

		// Initialize server object and serilazing classes
		server = new Server();
		kryo = server.getKryo();
		kryo.register(User.class);
		kryo.register(Message.class);

		// checks if the port from server.properties is null, if it is sets it
		// to default 54555;
		PORT = serverProperties.getValue("port") != null ? (String) serverProperties.getValue("port") : "54555";
		int port = Integer.parseInt(PORT);

		// Starts server & binds port;
		System.out.println("Starting server...");
		server.start();
		System.out.println("Binding server to port: " + PORT);
		server.bind(port-1,port);
		
		System.out.println("Server binded to port: " + PORT);
		System.out.println("Server started");

		// Server event listener
		server.addListener(new Listener() {

			public void received(Connection connection, Object object) {
				if (object instanceof Message) {

					Message message = (Message) object;
					server.sendToAllUDP(message);
					System.out.println(message.getMessage());

				} else if (object instanceof User) {
					User user = (User) object;

					users.add(user);
					Message m = new Message().setUser(new User().setClientId("god").setUsername("Server"))
							.setMessage(user.getUsername() + " " + user.getClientId() + " has connected");

					server.sendToAllUDP(m);

					System.out.println(user.getUsername() + " connected");

				}

			}

			public void connected(Connection connection) {
			
			}

			public void disconnected(Connection connection) {
				System.out.println("User disconnected");
			}

		});
		
		
		

	}

}
