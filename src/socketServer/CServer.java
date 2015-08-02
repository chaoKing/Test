/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-7-13
 */
package socketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author �̳���
 * 
 */
public class CServer {

	public static ArrayList<Socket> socketList = new ArrayList<Socket>();

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(30000);
		System.out.println("serverSocket start!!!");
		while (true) { 
			Socket s = serverSocket.accept();
			System.out.println("�����ӣ�" + s.getInetAddress());
			socketList.add(s);
			System.out.println("��������" + socketList.size());
			new Thread(new ServerThread(s)).start();
		}
	}

}

class ServerThread implements Runnable {
	private Socket s = null;

	BufferedReader br = null;

	public ServerThread(Socket s) throws IOException {
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream(),
				"utf-8"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		String content = null;
		while ((content = readFromClient()) != null) {
			for (Socket s : CServer.socketList) {
				OutputStream os;
				try {
					os = s.getOutputStream();
					System.out.println(content);
					os.write((content + "\n").getBytes("utf-8"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String readFromClient() {
		try {
			return br.readLine();
		} catch (IOException e) {
			System.out.println("error");
			CServer.socketList.remove(s);
			e.printStackTrace();
		}
		return null;
	}

}
