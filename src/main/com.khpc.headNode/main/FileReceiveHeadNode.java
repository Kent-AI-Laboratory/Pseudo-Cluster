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

    private void receiveFile() throws IOException, InterruptedException {
        // Defining the server socket
        setServSoc(new ServerSocket(getPort()));

        // Defining the variable for reading the file
        int bytesRead;

        System.out.println("ServerTransferFile: Establishing socket");

        while (true) {
            // Connecting to the client socket
            Socket clientSoc = getServSoc().accept();
            System.out.println("ServerTransferFile: Client connected");

            // Defining the socket output (inputStream) and the file output (outputstream)
            InputStream inStream = clientSoc.getInputStream();
            OutputStream output = new FileOutputStream(getFileStorePath() + getFileName());

            // Read the write the file to the fileStorePath
            byte[] byteArray = new byte[1024];
            while ((bytesRead = inStream.read(byteArray)) != -1) {
                output.write(byteArray, 0, bytesRead);
            }

            //Start the file distribution
            startFileDistribution();

            // Close serversocket and outputstream, break the loop
            getServSoc().close();
            output.close();
            break;
        }
    }

    private void startFileDistribution() throws IOException, InterruptedException{
        NodeList nodeLst = new NodeList();
        nodeLst.scanAndAdd();

        FileDistribution.distributeFile(new File(getFileStorePath()+getFileName()),50,50);
    }
}
