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

import java.io.IOException;
import java.util.Scanner;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Main {

	static String ip = "", port = "";

	static User user = new User();

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Client client = new Client();
		client.start();
		Kryo kryo = client.getKryo();
		kryo.register(User.class);
		kryo.register(Message.class);

		// gets server ip & port if entered
		System.out.println("please enter server ip\n");
		int x = 0;
		String i = input.nextLine();
		for (char c : i.toCharArray()) {
			if (c == ':') {
				ip = i.substring(0, x);
				port = i.substring(x + 1, i.length());
				break;
			}
			x++;
		}

		// if no port was entered it sets it to 54555.
		// checks the input for an ip, if no ip sets it localhost
		if (ip.equalsIgnoreCase("")) {
			ip = i.equalsIgnoreCase("") ? "localhost" : i;
			port = "54555";
		}

		// if user entered the : but no port, it sets it to the default
		if (port.equalsIgnoreCase("")) {
			port = "54555";
		}

		//keeps prompting for username if user hasnt enterted one
		while (user.getUsername().equalsIgnoreCase("")) {
			System.out.println("please enter your username\n");
			user.setUsername(input.nextLine());
		}

		
		try {
			client.connect(5000, ip, Integer.parseInt(port) - 1, Integer.parseInt(port));
			System.out.println("connected");
			user.setClientId(String.valueOf(client.getID()));
			client.sendUDP(user);
		} catch (IOException e) {
			e.printStackTrace();
			input.close();
		}

		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof Message) {
					Message m = (Message) object;
					User u = m.getUser();

					if (!user.equals(u)) {
						System.out.println(u.getUsername() + " " + u.getClientId() + " : " + m.getMessage());
					}
				}
			}
		});

		while (true) {
			Message m = new Message().setMessage(input.nextLine()).setUser(user);
			client.sendUDP(m);
		}

	}

}
