package clientTransfer;

import java.io.IOException;
import java.net.UnknownHostException;

public class ClientTransferCoordinator {
	// String: 7000, File 5000
	
	public static void sendFileOnce(String filePath, String servIpAddr)
			throws UnknownHostException, IOException, InterruptedException {
		// Initialize string transfer module and send the string
		ClientTransferString clientStringTrans = new ClientTransferString(filePath, servIpAddr);
		clientStringTrans.sendString();

		// Allow enough time for the invocation to happen on the server
		Thread.sleep(3000);

		// Initialize file transfer module and transfer the file
		ClientTransferFile clientFileTrans = new ClientTransferFile(filePath, servIpAddr);
		clientFileTrans.sendFile();
	}
}
