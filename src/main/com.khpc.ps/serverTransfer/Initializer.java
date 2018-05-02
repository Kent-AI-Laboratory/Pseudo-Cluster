package serverTransfer;

import java.io.IOException;

public class Initializer {
	public static void main(String args[]) {
		try {
			ServerTransferCoordinator.startReceiveServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
