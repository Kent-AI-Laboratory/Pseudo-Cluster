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
	private String serverIP;
	private int serverPort;
	private String filePath;

	private Socket socket;

	public ClientTransferFile(String serverIP, int serverPort, String filePath) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.filePath = filePath;
	}

	// ####Debug only
	public ClientTransferFile(String filePath) throws UnknownHostException {
		this(InetAddress.getLocalHost().getHostAddress(), 5000, filePath);
	}

	public void sendFile() throws UnknownHostException, IOException {
		socket = new Socket(serverIP, serverPort);

		System.out.println("Server connection established");

		File file = new File(filePath);
		byte[] byteArray = new byte[(int) file.length()];

		FileInputStream fileInput = new FileInputStream(file);
		BufferedInputStream bufferedIn = new BufferedInputStream(fileInput);
		bufferedIn.read(byteArray, 0, byteArray.length);

		OutputStream outStream = socket.getOutputStream();
		outStream.write(byteArray, 0, byteArray.length);

		System.out.println("File transfer finished");

		outStream.flush();
		socket.close();
	}
}
