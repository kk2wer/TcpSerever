package com.leemeenho.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class TcpServer {
	private Scanner scan;
	HashMap<String, String> domain;
	private int port;
	private int count;
	public TcpServer() {
		super();
		scan = new Scanner(System.in);
		port = 8888;
		count = 0;
		domain = new HashMap<String, String>();
		domain.put("www.naver.com", "125.209.222.142");
		domain.put("www.google.com", "172.217.175.4");
		domain.put("www.iei.or.kr", "211.43.14.187");
	}
	public void tcpServer() {
		ServerSocket server = null;
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		System.out.println("made server now");
		try {
			server = new ServerSocket(port);
			System.out.println("DNS Server Ready");
			while(true) {
			System.out.println("Client Accept Ready");
			Socket socket = server.accept();
			System.out.println("accept Client");
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			dataInputStream = new DataInputStream(inputStream);
				String clientRequest = dataInputStream.readUTF();
				if(domain.containsKey(clientRequest)) dataOutputStream.writeUTF(domain.get(clientRequest));
				else dataOutputStream.writeUTF("not exists address");
				System.out.println("complate "+ ++count +" response");
				System.out.print("close server? [y/n] : ");
				if(scan.next().equals("y")) break; 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dataInputStream.close();
				dataOutputStream.close();
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}