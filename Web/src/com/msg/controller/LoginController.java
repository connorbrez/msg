package com.msg.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.msg.server.objects.Message;
import com.msg.server.objects.User;

@Controller
public class LoginController {

	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
 
		User user = new User().setUsername("Web Client");

		Client client = new Client();
		client.start();
		Kryo kryo = client.getKryo();
		kryo.register(User.class);
		kryo.register(Message.class);
		String message;
		
		try {
			client.connect(5000, "localhost", 54555-1 ,54555);
			System.out.println("connected");
			user.setClientId(String.valueOf(client.getID()));
			client.sendUDP(user);
			 message = "<br><div style='text-align:center;'>"
						+ "connected </div><br><br>";
		} catch (IOException e) {
			 message = "<br><div style='text-align:center;'>"
						+ "not connected </div><br><br>";
			e.printStackTrace();
		}
		
		
		
		return new ModelAndView("welcome", "message", message);
	}
	
}
