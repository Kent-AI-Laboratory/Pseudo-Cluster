package main;

import java.io.IOException;

import receiveFile.FileReceiveCoordinator;

public class FileReceiveBackground {
	public static void startSend(String fileStorePath) {
		FileReceiveCoordinator receiveCor = new FileReceiveCoordinator();
		receiveCor.setFileStorePath(fileStorePath);
		Thread sendCorSend = new Thread(receiveCor);
		sendCorSend.start();
	}
}
