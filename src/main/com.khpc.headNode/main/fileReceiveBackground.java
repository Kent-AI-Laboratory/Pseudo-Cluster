package main;

import java.io.IOException;

import receiveFile.FileSendCoordinator;

public class fileReceiveBackground {
	public static void startSend(String fileStorePath) {
		FileSendCoordinator sendCor = new FileSendCoordinator();
		sendCor.setFileStorePath(fileStorePath);
		Thread sendCorSend = new Thread(sendCor);
		sendCorSend.start();
	}
}
