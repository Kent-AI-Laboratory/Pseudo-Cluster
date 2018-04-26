package clientTransfer;

import java.io.IOException;
import java.net.UnknownHostException;

public class ClientTransferCoordinator {
	public static void main(String args[]) {
		try {
			ClientTransferString clientStringTrans = new ClientTransferString("something.sh");
			clientStringTrans.sendString();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ClientTransferFile clientFileTrans = new ClientTransferFile("/home/aaronmao/Desktop/something.sh");
			clientFileTrans.sendFile();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
