package hw_udptime;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPTimeServer {
	private static final int PORT = 5000;
	private static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
		String data = null;
		byte[] sendBuffer;
		
		DatagramSocket socket = null;
		DatagramPacket receivePacket = null, sendPacket = null;
		
		try {
			socket = new DatagramSocket(PORT);
			receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], 
					BUFFER_SIZE);
			
			while(true) {
				socket.receive(receivePacket);
				
				data = format.format(new Date());
				System.out.println("Client Requested time at " + data);
				sendBuffer = data.getBytes("utf-8");
				sendPacket = new DatagramPacket(sendBuffer, 0, sendBuffer.length,
						new InetSocketAddress(receivePacket.getAddress(), receivePacket.getPort()));
				socket.send(sendPacket);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		}
	}
}
