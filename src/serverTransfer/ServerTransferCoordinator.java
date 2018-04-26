package serverTransfer;

import java.io.IOException;

public class ServerTransferCoordinator {
	public static void main(String args[]) {
		//ServerTransferFile servFile = new ServerTransferFile("/home/aaronmao/Documents");
		try {
			ServerTransferString servStringTrans = new ServerTransferString();
			ServerTransferFile servFileTrans = new ServerTransferFile();
			
			servStringTrans.setFileTrans(servFileTrans);
			Thread tServStringTrans = new Thread(servStringTrans);
			tServStringTrans.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
