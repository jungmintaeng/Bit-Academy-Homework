package hw_udptime;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPTimeClient {
	private static final String SERVER_IP = "192.168.1.17";
	private static final int SERVER_PORT = 5000;
	private static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		byte[] receiveBuffer = new byte[BUFFER_SIZE];
		
		try {
			socket = new DatagramSocket();
			DatagramPacket sendPacket = new DatagramPacket("".getBytes("utf-8"), 0, 0,
					new InetSocketAddress(SERVER_IP, SERVER_PORT));
			socket.send(sendPacket);
			
			DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, BUFFER_SIZE);
			socket.receive(receivePacket);
			System.out.println(new String(receiveBuffer, 0, receivePacket.getLength(), "utf-8"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
