package clientTransfer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTransferString {
	private Socket socket;
	private static String target;
	private static int port;
	private static String ipaddr;
	
	private PrintWriter writer;

	public ClientTransferString() {}
	
	public ClientTransferString(String target) throws UnknownHostException {
		this(target, 7000);
	}
	
	public ClientTransferString(String target, int port) throws UnknownHostException {
		this.target = target;
		this.port = port;
		ipaddr = InetAddress.getLocalHost().getHostAddress();
	}

	public void sendString() throws UnknownHostException, IOException {
		System.out.println("Attempting to connect to the server");
		socket = new Socket(ipaddr, port);
		
		writer = new PrintWriter(socket.getOutputStream(),true);
		writer.println(target);
		System.out.println("Connection to Server Successful");
	}
}
