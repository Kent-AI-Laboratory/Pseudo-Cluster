package main;

import receiveFile.ReceiveFile;
import runScript.RunFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceiveNode extends ReceiveFile {

    public FileReceiveNode() throws IOException {
        super();
    }

    public void postReceiveOperation(){
        RunFile tempRun = new RunFile(getFileStorePath(), getFileName(), "");
        tempRun.detectAndRun();
    }
}
