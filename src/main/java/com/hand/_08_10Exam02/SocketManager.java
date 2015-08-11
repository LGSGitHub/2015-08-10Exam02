package com.hand._08_10Exam02;

import java.io.File;
import java.util.Vector;


//把pdf文件发送给各个客户端
public class SocketManager{

	//单例化
	private SocketManager(){}
	private static final SocketManager sm = new SocketManager();
	public static SocketManager getSocketManager()
	{
		return sm;
	}
	//用于存放客户端的Socket
	Vector<ClientSocket> vector = new Vector<ClientSocket>();
	//把客户端的Socket添加到vector
	public void addClientSocket(ClientSocket cs)
	{
		vector.add(cs);
	}
	
	//发送文件的路径给每个客户端
	public void output()
	{
		File file = new File("C://HandWork/Exam/2015-08-10Exam01/SampleChapter1.pdf");
		for (int i = 0; i < vector.size(); i++) {
			ClientSocket clientSocket = vector.get(i);
			//调用客户端的outputFile方法将文件写出到本地
			clientSocket.outputFile(file);
		}
	}
}
