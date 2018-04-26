package serverTransfer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTransferFile implements Runnable {
	private String fileName;
	private String filePath;
	private int port;

	private Socket clientSoc;
	private ServerSocket servSoc;
	private File file;

	public ServerTransferFile(String filePath, String fileName, int port) throws IOException {
		this.filePath = filePath;
		this.fileName = fileName;
		this.port = port;
	}

	public ServerTransferFile() throws IOException {
		this("/home/aaronmao/Documents/","", 5000);
	}

	public void receiveFile() throws IOException {
		System.out.println(fileName);
		servSoc = new ServerSocket(port);
		file = new File(fileName);

		int bytesRead;
		int current = 0;

		System.out.println("Establishing socket");
		boolean isConnected = false;

		while (true) {
			Socket clientSoc = servSoc.accept();
			if (!isConnected)
				System.out.println("Client connected");

			InputStream inStream = clientSoc.getInputStream();
			OutputStream output = new FileOutputStream(filePath+fileName);

			byte[] byteArray = new byte[1024];
			while ((bytesRead = inStream.read(byteArray)) != -1) {
				output.write(byteArray, 0, bytesRead);
			}
			servSoc.close();
			output.close();
			break;
		}
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		try {
			receiveFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
