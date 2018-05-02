package receiveFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class sendString implements Runnable {

	// Define the fields necessary for TCP
	private int port;
	private Socket clientSoc;
	private ServerSocket serverSoc;

	// Define string BufferedReader
	BufferedReader reader;

	// The session running file receive
	private sendFile fileTrans;

	protected sendString(int port) {
		this.port = port;
	}

	protected sendString() {
		this(7000);
	}

	private void startServer() throws IOException {
		// Initialize and starting the new server socket
		serverSoc = new ServerSocket(port);
		System.out.println("ServerTransferString: Server connection successful");

		// Keeping the string receive server constantly on
		while (true) {
			System.out.println("Waiting for client connection");
			clientSoc = serverSoc.accept();
			reader = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
			getClientString();
		}
	}

	private void getClientString() throws IOException {
		//Transfer the file path to file receive module
		try {
			startFileReceive(reader.readLine());
		} catch (Exception e) {
			System.out.println("Error in getClientString()");
			e.printStackTrace();
		}
		
		//Check whether reader and soc are null
		if (reader != null) {
			reader.close();
		}
		if (clientSoc != null) {
			clientSoc.close();
		}
	}
	
	private void startFileReceive(String fileName) {
		fileTrans.setFileName(fileName);
		fileTrans.run();
	}

	protected void setFileTrans(sendFile fileTrans) {
		this.fileTrans = fileTrans;
	}

	@Override
	public void run() {
		try {
			startServer();
			getClientString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
