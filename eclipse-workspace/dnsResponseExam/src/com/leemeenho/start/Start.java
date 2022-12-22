package com.leemeenho.start;

import com.leemeenho.func.TcpClient;

public class Start {

	public static void main(String[] args) {
		//TcpServer tcpServer = new TcpServer();
		//tcpServer.tcpServer();
		TcpClient tcpClient = new TcpClient();
		tcpClient.tcpClient();
	}
}