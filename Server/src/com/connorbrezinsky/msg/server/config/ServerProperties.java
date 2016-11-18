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

 package com.connorbrezinsky.msg.server.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.connorbrezinsky.msg.server.main.Main;

public class ServerProperties extends Config {

	public ServerProperties() {
		super("server.properties");
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
