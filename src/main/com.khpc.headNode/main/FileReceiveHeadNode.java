package main;

import receiveFile.ReceiveFile;
import sendFile.FileSendCoordinator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceiveHeadNode extends ReceiveFile {

    public FileReceiveHeadNode() throws IOException {
        super();
    }

    public void postReceiveOperation() throws IOException, InterruptedException{
        NodeList nodeLst = new NodeList();
        nodeLst.scanAndAdd();

        FileDistribution.distributeFile(new File(getFileStorePath()+getFileName()),50,50);
    }
}
