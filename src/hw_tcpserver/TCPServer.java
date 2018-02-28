package hw_tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	private static int SERVER_PORT = 8888;
	
	public static void main(String[] args) {
		ServerSocket serverSocket= null;
		Socket socket = null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		String message;
		
		try {
			serverSocket = new ServerSocket();
			InetAddress localhost = InetAddress.getLocalHost();
			serverSocket.bind(new InetSocketAddress(localhost.getHostAddress(), SERVER_PORT));
			
			print("연결 기다림");
			socket = serverSocket.accept();
			print("연결됨 from " + socket.getInetAddress().getHostAddress() + ":" + 
					((InetSocketAddress)(socket.getRemoteSocketAddress())).getPort());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			
			while(true) {
				message = reader.readLine();
				if(message == null) {
					print("클라이언트로부터 연결 끊김");
					break;
				}
				System.out.println(message);
				writer.println(message);
				writer.flush();
			}
		}catch(ConnectException cex) {
			cex.printStackTrace();
		}catch(IOException ioex) {
			ioex.printStackTrace();
		}finally {
			if(socket != null && !socket.isClosed()) {
				try {
					socket.close();
				}catch(IOException ioex) {
					ioex.printStackTrace();
				}
			}
			if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(writer != null)
				writer.close();
		}
	}
	
	private static void print(String message) {
		System.out.println("[서버] " + message);
	}
}
