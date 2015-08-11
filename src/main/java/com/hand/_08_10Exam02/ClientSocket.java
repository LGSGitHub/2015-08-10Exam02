package com.hand._08_10Exam02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

//从服务器端得到pdf文件并保存到本地
public class ClientSocket extends Thread {

	Socket socket;

	public ClientSocket(Socket s) {
		this.socket = s;
	}

	//从服务器接受文件路径，然后用字节流保存本地（该工程的根目录）
	public void outputFile(File file) {
		try {

			//用fileinputstream 读取数据
			FileInputStream fis = new FileInputStream(file);
			//用带有缓冲的bufferedinputstream包装fis对象
			BufferedInputStream bis = new BufferedInputStream(fis);

			//用fileoutputstream写出数据
			FileOutputStream fos = new FileOutputStream("SampleChapter1.pdf");
			//用缓冲包装fos对象
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			//创建字节数组保存数据
			byte[] b = new byte[50];
			int len = 0;

			String wait = "下载中，感谢您的耐心等待……";
			System.out.println(wait);
			//在客户端输出文字
			socket.getOutputStream().write(wait.getBytes("GBK"));
			//利用字节数组一个一个地写入，边写入边写出
			while ((len = bis.read(b)) != -1) {
				bos.write(b);
			}
			String finish = "下载完成，文件已经保存到本工程的根目录！";
			System.out.println(finish);
			socket.getOutputStream().write(finish.getBytes("GB2312"));

			bos.close();
			fos.close();
			bis.close();
			fis.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		//每个客户端一连接就请求服务器发送文件路径
		SocketManager.getSocketManager().output();
	}
}
