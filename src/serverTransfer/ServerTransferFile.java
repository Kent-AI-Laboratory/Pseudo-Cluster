package serverTransfer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTransferFile implements Runnable {
	private String filePath;
	private int port;

	private Socket clientSoc;
	private ServerSocket servSoc;
	private File file;

	public ServerTransferFile(String filePath, int port) throws IOException {
		this.filePath = filePath;
		this.port = port;
		receiveFile();
	}

	public ServerTransferFile(String filePath) throws IOException {
		this(filePath, 5000);
	}

	public void receiveFile() throws IOException {
		servSoc = new ServerSocket(port);
		file = new File(filePath);

		int bytesRead;
		int current = 0;
		
		System.out.println("Establishing socket");
		boolean isConnected = false;
		
		while(true) {
			Socket clientSoc = servSoc.accept();
			if(!isConnected)
				System.out.println("Client connected");
			
			InputStream inStream = clientSoc.getInputStream();
			OutputStream output = new FileOutputStream(filePath);
			
			byte[] byteArray = new byte[1024];
			while((bytesRead = inStream.read(byteArray))!=-1) {
				output.write(byteArray,0,bytesRead);
			}
			
			output.close();
		}
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
	
	public static void main(String args[]) {
		try {
			ServerTransferFile demo = new ServerTransferFile("/home/aaronmao/Documents/something.sh");
			Thread tdemo = new Thread(demo);
			tdemo.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
