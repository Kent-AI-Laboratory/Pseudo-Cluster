package clientTransfer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTransferString {
	private Socket socket;
	private static String target;
	private static int servPort;
	private static String servIpAddr;

	private PrintWriter writer;

	protected ClientTransferString(String target) throws UnknownHostException {
		this(target, InetAddress.getLocalHost().getHostAddress());
	}

	protected ClientTransferString(String target, String servIpAddr) throws UnknownHostException {
		this(target, 7000, servIpAddr);
	}

	protected ClientTransferString(String target, int servPort, String servIpAddr) {
		this.target = target;
		this.servPort = servPort;
		this.servIpAddr = servIpAddr;
	}

	protected void sendString() throws UnknownHostException, IOException {
		// Connect the client to the server
		System.out.println("Client: Attempting to connect to the server");
		socket = new Socket(servIpAddr, servPort);

		// Write the string to the socket
		writer = new PrintWriter(socket.getOutputStream(), true);
		writer.println(target);
		System.out.println("Client: Connection to Server Successful");
	}
}
