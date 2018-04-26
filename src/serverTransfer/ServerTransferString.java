package serverTransfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTransferString implements Runnable{
	
	// Define the fields necessary for TCP
	private int port;
	private Socket clientSoc;
	private ServerSocket serverSoc;

	// Define string BufferedReader
	BufferedReader reader;

	public ServerTransferString(int port) {
		this.port = port;
	}
	
	public ServerTransferString() {
		this(7000);
	}

	public void getServer() throws IOException {
		serverSoc = new ServerSocket(port);
		System.out.println("Server connection successful");
		while (true) {
			System.out.println("Waiting for client connection");
			clientSoc = serverSoc.accept();
			reader = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
			getClientString();
		}
	}

	public void getClientString() throws IOException {
		try {
			while (true) {
				System.out.println("Client message: " + reader.readLine());
				break;
			}
		} catch (Exception e) {
			System.out.println("Error in getClientString");
			e.printStackTrace();
		}
		if (reader != null) {
			reader.close();
		}
		if (clientSoc != null) {
			clientSoc.close();
		}
	}

	@Override
	public void run() {
		try {
			getClientString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
