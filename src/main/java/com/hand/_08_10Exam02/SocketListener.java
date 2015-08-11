package com.hand._08_10Exam02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class SocketListener extends Thread {

	@Override
	public void run() {

		try {

			ServerSocket serversocket = new ServerSocket(12345);

			while (true) {
				Socket socket = serversocket.accept();
				JOptionPane.showMessageDialog(null, "有客户端连接到12345端口");
				// 将socket传递给新的线程
//				ChatSocket cs = new ChatSocket(socket);
//				cs.start();
//				SocketManager.getChatManager().addChatSocket(cs);
//				SocketManager.getOutputSocket().add(socket);
				
				ClientSocket cs = new ClientSocket(socket);
				cs.start();
				SocketManager.getSocketManager().addClientSocket(cs);
				

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
