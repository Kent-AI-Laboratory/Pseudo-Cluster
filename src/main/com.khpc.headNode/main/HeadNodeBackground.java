package main;

import java.io.IOException;

import serverTransfer.ServerTransferCoordinator;

public class HeadNodeBackground {
	public static void main(String args[]) {
		try {
			ServerTransferCoordinator.startReceiveServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
