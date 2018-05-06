package serverTransfer;

import java.io.IOException;

public class ServerTransferCoordinator {
	
	public static void startReceiveServer() throws IOException {
		//Initializing server for file and string transfer
		ServerTransferString servStringTrans = new ServerTransferString();
		ServerTransferFile servFileTrans = new ServerTransferFile();

		//Set the invoke target for string transfer module
		servStringTrans.setFileTrans(servFileTrans);
		
		//Where the file will be stored, please change
		servFileTrans.setfileStorePath("/home/aaronmao/Documents/");
		
		//Start running the string transfer server as a thread
		Thread tServStringTrans = new Thread(servStringTrans);
		tServStringTrans.start();
	}
}
