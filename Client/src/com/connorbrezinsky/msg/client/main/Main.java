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

		while (user.getUsername().equalsIgnoreCase("")) {
			System.out.println("please enter your username\n");
			user.setUsername(input.nextLine());
		}

		try {
			client.connect(5000, ip, Integer.parseInt(port)-1, Integer.parseInt(port));

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
