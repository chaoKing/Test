/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-7-13
 */
package socketServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 蔡超敏
 * 
 */
public class SimpleServer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket sSocket = new ServerSocket(30001);
		System.out.println("server start!");
		while (true) {
			Socket socket = sSocket.accept();
			if (socket.isConnected()) {
				System.out.println("socket connect!" + socket.getInetAddress());
			}
			OutputStream os = socket.getOutputStream();
			os.write("呵呵".getBytes("utf-8"));
			os.close();
			socket.close();
		}
	}

}
