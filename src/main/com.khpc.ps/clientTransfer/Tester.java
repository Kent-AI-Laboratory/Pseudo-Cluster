package clientTransfer;

import java.io.IOException;

public class Tester {
	public static void main(String args[]) {
		try {
			ClientTransferCoordinator.sendFileOnce("/home/aaronmao/Desktop/something", "127.0.0.1");
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
