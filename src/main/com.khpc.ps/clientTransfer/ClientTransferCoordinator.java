package clientTransfer;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

public class ClientTransferCoordinator {
	// String: 7000, File 5000
	protected static void sendFileOnce(File file, String servIpAddr)
			throws UnknownHostException, IOException, InterruptedException {
		// Initialize string transfer module and send the string
		ClientTransferString clientStringTrans = new ClientTransferString(file.getName(), servIpAddr);
		clientStringTrans.sendString();

		// Allow enough time for the invocation to happen on the server
		Thread.sleep(3000);

		// Initialize file transfer module and transfer the file
		ClientTransferFile clientFileTrans = new ClientTransferFile(file.getPath(), servIpAddr);
		clientFileTrans.sendFile();
	}
}
