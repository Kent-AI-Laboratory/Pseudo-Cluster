package clientTransfer;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public class ClientTransferRepeater {
	
	//Continuously send files
	public static void sendFile(List<File> fileList, String servIpAddr)
			throws UnknownHostException, IOException, InterruptedException {
		for (File file : fileList) {
			ClientTransferCoordinator.sendFileOnce(file, servIpAddr);
		}
	}
}