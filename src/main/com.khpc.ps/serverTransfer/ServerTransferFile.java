package serverTransfer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTransferFile implements Runnable {
	
	//The field necessary for file storage
	private String fileStorePath;
	private String fileName;
	private String filePath;
	private File file;
	
	private int port;
	private Socket clientSoc;
	private ServerSocket servSoc;

	protected ServerTransferFile(String fileStorePath, int port) throws IOException {
		this.fileStorePath = fileStorePath;
		this.port = port;
	}

	protected ServerTransferFile() throws IOException {
		this("", 5000);
	}

	private void receiveFile() throws IOException {
		//Defining the server socket
		servSoc = new ServerSocket(port);
		
		//Defining the variable for reading the file
		file = new File(fileStorePath);
		int bytesRead;

		System.out.println("ServerTransferFile: Establishing socket");

		while (true) {
			//Connecting to the client socket
			Socket clientSoc = servSoc.accept();
			System.out.println("ServerTransferFile: Client connected");

			//Defining the socket output (inputStream) and the file output (outputstream)
			InputStream inStream = clientSoc.getInputStream();
			OutputStream output = new FileOutputStream(filePath+fileName);

			//Read the write the file to the fileStorePath
			byte[] byteArray = new byte[1024];
			while ((bytesRead = inStream.read(byteArray)) != -1) {
				output.write(byteArray, 0, bytesRead);
			}
			
			//Close serversocket and outputstream, break the loop
			servSoc.close();
			output.close();
			break;
		}
	}

	protected void setfileStorePath(String fileStorePath) {
		this.fileStorePath = fileStorePath;
	}
	
	protected void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void run() {
		try {
			receiveFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
