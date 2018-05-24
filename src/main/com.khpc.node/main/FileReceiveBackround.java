package main;

import receiveFile.FileReceiveCoordinator;

public class FileReceiveBackround {
    public static void startReceive(String fileStorePath) {
        FileReceiveCoordinator receiveCor = new FileReceiveCoordinatorNode();
        receiveCor.setFileStorePath(fileStorePath);
        Thread receiveCorT = new Thread(receiveCor);
        receiveCorT.start();
    }
}
