package sendFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class receiveString {
	private Socket socket;
	private static String target;
	private static int servPort;
	private static String servIpAddr;

	private PrintWriter writer;

	protected receiveString(String target) throws UnknownHostException {
		this(target, InetAddress.getLocalHost().getHostAddress());
	}

	protected receiveString(String target, String servIpAddr) throws UnknownHostException {
		this(target, 7000, servIpAddr);
	}

	protected receiveString(String target, int servPort, String servIpAddr) {
		this.target = target;
		this.servPort = servPort;
		this.servIpAddr = servIpAddr;
	}

	protected void sendString() throws UnknownHostException, IOException {
		// Connect the client to the server
		System.out.println("Client: Attempting to connect to the server");
		socket = new Socket(servIpAddr, servPort);
		socket.setSoTimeout(10000);

		// Write the string to the socket
		writer = new PrintWriter(socket.getOutputStream(), true);
		writer.println(target);
		System.out.println("Client: Connection to Server Successful");
	}
}
