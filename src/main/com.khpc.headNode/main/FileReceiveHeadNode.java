package main;

import receiveFile.ReceiveFile;

import java.io.File;
import java.io.IOException;

public class FileReceiveHeadNode extends ReceiveFile {

    public FileReceiveHeadNode() throws IOException {
        super();
    }

    public void postReceiveOperation() throws IOException, InterruptedException {
        NodeList nodeLst = new NodeList();
        nodeLst.scanAndAdd();

        FileDistribution.distributeFile(new File(getFileStorePath() + getFileName()), 50, 50);
    }
}
