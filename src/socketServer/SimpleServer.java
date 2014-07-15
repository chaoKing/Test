/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-7-13
 */
package socketServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author �̳���
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
			os.write("�Ǻ�".getBytes("utf-8"));
			os.close();
			socket.close();
		}
	}

}
