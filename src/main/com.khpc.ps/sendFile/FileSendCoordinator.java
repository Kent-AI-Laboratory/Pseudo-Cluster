package sendFile;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

public class FileSendCoordinator {
	// String: 7000, File 5000
	public static void sendFileOnce(File file, String servIpAddr)
			throws UnknownHostException, IOException, InterruptedException {
		// Initialize string transfer module and send the string
		SendString clientStringTrans = new SendString(file.getName(), servIpAddr);
		clientStringTrans.sendString();

		// Allow enough time for the invocation to happen on the server
		Thread.sleep(3000);

		// Initialize file transfer module and transfer the file
		SendFile clientFileTrans = new SendFile(file.getPath(), servIpAddr);
		clientFileTrans.sendFile();
	}
}
