package clientTransfer;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public class ClientTransferRepeater {
	public static void sendFile(List<String> fileList, String servIpAddr)
			throws UnknownHostException, IOException, InterruptedException {
		for (String filePath : fileList) {
			ClientTransferCoordinator.sendFileOnce(filePath, servIpAddr);
		}
	}
}