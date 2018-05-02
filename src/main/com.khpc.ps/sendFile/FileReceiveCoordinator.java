package sendFile;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

public class FileReceiveCoordinator {
	// String: 7000, File 5000
	public static void sendFileOnce(File file, String servIpAddr)
			throws UnknownHostException, IOException, InterruptedException {
		// Initialize string transfer module and send the string
		receiveString clientStringTrans = new receiveString(file.getName(), servIpAddr);
		clientStringTrans.sendString();

		// Allow enough time for the invocation to happen on the server
		Thread.sleep(3000);

		// Initialize file transfer module and transfer the file
		receiveFile clientFileTrans = new receiveFile(file.getPath(), servIpAddr);
		clientFileTrans.sendFile();
	}
}
