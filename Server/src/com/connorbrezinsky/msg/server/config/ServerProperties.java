package com.connorbrezinsky.msg.server.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.connorbrezinsky.msg.server.main.Main;

public class ServerProperties extends Config {

	public ServerProperties() {
		super("server.properties");
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() throws IOException {
		// checks if file exisits, if not creates file and writes default
		// properties with default values;
		if (new File(file_path).createNewFile()) {
			System.out.println("Config file " + this.file + " created");
			output = new FileOutputStream(file_path);
			p.setProperty("port", "54555");
			p.setProperty("server_name", "msg default server name");
			p.setProperty("msg_version", Main.VERSION);
			p.store(output, null);
			output.close();
		}

		// loads .property file to read from.
		try {
			input = new FileInputStream(file_path);
			p.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

}
