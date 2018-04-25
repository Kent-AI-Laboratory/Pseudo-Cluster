package serverTransfer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ServerTransferFile implements Runnable {
	private String filePath;
	private int port;

	private Socket clientSoc;
	private ServerSocket servSoc;
	private File file;

	public ServerTransferFile(String filePath, int port) {
		this.filePath = filePath;
		this.port = port;
	}

	public ServerTransferFile(String filePath) {
		this(filePath, 5000);
	}

	public void receiveFile() throws IOException {
		servSoc = new ServerSocket(port);
		file = new File(filePath);

		int bytesRead;
		int current = 0;
		
		while(true) {
			Socket clientSoc = servSoc.accept();
			InputStream inStream = clientSoc.getInputStream();
			OutputStream 
		}
	}

	@Override
	public void run() {
		ServerTransferFile serverTrans = new ServerTransferFile("C:\\");
		Thread tServerTrans = new Thread(serverTrans);
	}
}
