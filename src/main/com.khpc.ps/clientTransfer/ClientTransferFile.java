package clientTransfer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTransferFile {
	private String servIpAddr;
	private int servPort;
	private String filePath;

	private Socket socket;

	protected ClientTransferFile(String servIpAddr, int servPort, String filePath) {
		this.servIpAddr = servIpAddr;
		this.servPort = servPort;
		this.filePath = filePath;
	}

	// Reload constructor
	protected ClientTransferFile(String filePath, String servIpAddr) {
		this(servIpAddr, 5000, filePath);
	}

	// ####Debug only
	protected ClientTransferFile(String filePath) throws UnknownHostException {
		this(InetAddress.getLocalHost().getHostAddress(), 5000, filePath);
		System.out.println("Usage of this constructor is not recommended");
	}

	protected void sendFile() throws UnknownHostException, IOException {
		// Connect to server
		socket = new Socket(servIpAddr, servPort);
		System.out.println("Server connection established");

		// Defining file and read file and write the file to socket
		File file = new File(filePath);
		byte[] byteArray = new byte[(int) file.length()];
		FileInputStream fileInput = new FileInputStream(file);
		BufferedInputStream bufferedIn = new BufferedInputStream(fileInput);
		bufferedIn.read(byteArray, 0, byteArray.length);
		OutputStream outStream = socket.getOutputStream();
		outStream.write(byteArray, 0, byteArray.length);
		System.out.println("File transfer finished");

		// flush the stream and close the socket
		outStream.flush();
		socket.close();
	}
}