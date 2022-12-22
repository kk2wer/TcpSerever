package com.leemeenho.func;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {
	Scanner scan = new Scanner(System.in);
	public void tcpClient() {
		String serverIp = "192.168.10.96";
		int serverPortNumber = 9999;
		
		Socket socket = null;
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		try {
			socket = new Socket(serverIp, serverPortNumber);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			dataInputStream = new DataInputStream(inputStream);
			dataOutputStream = new DataOutputStream(outputStream);
			System.out.println("accept scocket");
			while(true) {
				String request = dataInputStream.readUTF();
				System.out.println(request);
				if(request.charAt(request.length()-1)=='?') dataOutputStream.writeUTF(scan.next());
				if(request.charAt(0) == '끝') break;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dataInputStream.close();
				dataOutputStream.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}