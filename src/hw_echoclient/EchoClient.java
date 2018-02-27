package hw_echoclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP = "192.168.1.17";

	//main 실행 인자로 port number
	public static void main(String[] args) {
		BufferedReader reader = null;
		PrintWriter writer = null;
		String message = null, receivedMessage = null;

		if(args.length != 1) {
			System.out.println("잘못된 인자입니다.");
			return;
		}

		if(!isStringDigit(args[0])) {
			System.out.println("잘못된 인자입니다.");
			return;
		}

		final int PORT = Integer.parseInt(args[0]);

		Socket socket = new Socket();
		Scanner scanner = new Scanner(System.in);

		try {
			socket.connect(new InetSocketAddress(SERVER_IP, PORT));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			
			while(true) {
				System.out.print(">>");
				message = scanner.nextLine();
				if("exit".equals(message))
					break;
				writer.println(message);
				writer.flush();
				System.out.print("<<");
				receivedMessage = reader.readLine();
				System.out.println(receivedMessage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(socket != null && !socket.isClosed()) {
				try {
					socket.close();
				}catch(IOException ioex) {
					ioex.printStackTrace();
				}
			}
			
			scanner.close();
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException ioex) {
					ioex.printStackTrace();
				}
			}
			
			if(writer != null)
				writer.close();
		}
	}

	private static boolean isStringDigit(String str) {
		int len = str.length();
		for(int i = 0 ; i < len; i++) {
			if(!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}
}
