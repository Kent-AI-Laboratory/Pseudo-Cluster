package main;

import receiveFile.ReceiveFile;
import runScript.RunFile;

import java.io.IOException;

public class FileReceiveNode extends ReceiveFile {

    public FileReceiveNode() throws IOException {
        super();
    }

    public void postReceiveOperation() {
        RunFile tempRun = new RunFile(getFileStorePath(), getFileName(), "");
        tempRun.detectAndRun();
    }
}
