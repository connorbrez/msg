package com.connorbrezinsky.msg.client.main;

public class Server {

	private String ip, port, name;
	
	public Server(String ip, String port, String name){
		this.setIp(ip);
		this.setPort(port);
		this.setName(name);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
