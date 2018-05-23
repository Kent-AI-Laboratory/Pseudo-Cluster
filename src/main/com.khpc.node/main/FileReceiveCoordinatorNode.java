package main;

import receiveFile.FileReceiveCoordinator;
import receiveFile.ReceiveFile;
import receiveFile.ReceiveString;

import java.io.IOException;

public class FileReceiveCoordinatorNode extends FileReceiveCoordinator {

    public void startReceiveServer () throws IOException {
        //Initializing server for file and string transfer
        ReceiveString servStringTrans = new ReceiveString();
        ReceiveFile servFileTrans = new FileReceiveNode();

        //Set the invoke target for string transfer module
        servStringTrans.setFileTrans(servFileTrans);

        //Where the file will be stored, please change
        servFileTrans.setfileStorePath(getFileStorePath());

        //Start running the string transfer server as a thread
        Thread tServStringTrans = new Thread(servStringTrans);
        tServStringTrans.start();
    }
}
