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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Config {

	protected Properties p = new Properties();
	protected InputStream input = null;
	protected OutputStream output = null;
	public static final String HOME_DIR = System.getProperty("user.home");
	public static final String SAVE_DIR = "msg";

	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	String file_path;
	String file;

	public Config(String file) {
		this.file_path = HOME_DIR + File.separator + SAVE_DIR + File.separator + file;
		this.file = file;

		try {
			load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	//
	public void load() throws IOException {

		
		//checks if file exisits, if not creates file;
		if (new File(file_path).createNewFile()) { 
			System.out.println("Config file " + this.file + " created");
		}

		//loads .property file to read from;
		try {
			input = new FileInputStream(file_path);
			p.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	
	public Object getValue(String key) {
		return p.get(key);
	}

	//must close the input stream manually after getting all properties from the config file
	public void close() throws IOException{
		input.close();
	}
	
	
}
