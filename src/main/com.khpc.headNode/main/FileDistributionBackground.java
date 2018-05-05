package main;

import java.util.ArrayList;

import receiveFile.FileReceiveCoordinator;

public class FileDistributionBackground {
	private ArrayList<Node> nodeList;
	private FileReceiveCoordinator sendCor;
	
	public FileDistributionBackground() {
		nodeList = new ArrayList<Node>();
		sendCor = new FileReceiveCoordinator();
	}
	
	
}
