package hw_nslookup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		InetAddress[] hosts;
		
		while(!"exit".equals(input)) {
			System.out.print("> ");
			input = scanner.nextLine();
			try {
				hosts = InetAddress.getAllByName(input);
			} catch (UnknownHostException e) {
				hosts = null;
				System.out.println("해당 호스트는 존재하지 않습니다.");
			}
			
			if(hosts != null) {
				for(int i = 0; i < hosts.length; i++) {
					System.out.println(hosts[i].getHostAddress());
				}
			}
		}
		
		scanner.close();
	}
}
