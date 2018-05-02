package main;

import java.io.IOException;

import receiveFile.FileSendCoordinator;

public class fileReceiveBackground {
	public static void main(String args[]) {
		try {
			FileSendCoordinator.startReceiveServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}
