package receiveFile;

import java.io.IOException;

public class FileSendCoordinator {
	
	public static void startReceiveServer() throws IOException {
		//Initializing server for file and string transfer
		sendString servStringTrans = new sendString();
		sendFile servFileTrans = new sendFile();

		//Set the invoke target for string transfer module
		servStringTrans.setFileTrans(servFileTrans);
		
		//Where the file will be stored, please change
		servFileTrans.setfileStorePath("/home/aaronmao/Documents/");
		
		//Start running the string transfer server as a thread
		Thread tServStringTrans = new Thread(servStringTrans);
		tServStringTrans.start();
	}
}
