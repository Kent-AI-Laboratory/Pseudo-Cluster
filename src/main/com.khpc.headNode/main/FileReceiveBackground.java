package main;

import java.io.IOException;

import receiveFile.FileReceiveCoordinator;

public class FileReceiveBackground {
	public static void startSend(String fileStorePath) {
		FileReceiveCoordinator sendCor = new FileReceiveCoordinator();
		sendCor.setFileStorePath(fileStorePath);
		Thread sendCorSend = new Thread(sendCor);
		sendCorSend.start();
	}
}
