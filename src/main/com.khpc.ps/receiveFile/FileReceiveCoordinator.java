package receiveFile;

import java.io.IOException;

public class FileReceiveCoordinator implements Runnable {
	
	private String fileStorePath;
	
	public void startReceiveServer () throws IOException {
		//Initializing server for file and string transfer
		receiveString servStringTrans = new receiveString();
		receiveFile servFileTrans = new receiveFile();

		//Set the invoke target for string transfer module
		servStringTrans.setFileTrans(servFileTrans);
		
		//Where the file will be stored, please change
		servFileTrans.setfileStorePath(fileStorePath);
		
		//Start running the string transfer server as a thread
		Thread tServStringTrans = new Thread(servStringTrans);
		tServStringTrans.start();
	}
	
	public void setFileStorePath(String fileStorePath) {
		this.fileStorePath = fileStorePath;
	}

	@Override
	public void run() {
		try {
			startReceiveServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
