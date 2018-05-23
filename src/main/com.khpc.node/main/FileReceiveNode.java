package main;

import receiveFile.receiveFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceiveNode extends receiveFile{

    public FileReceiveNode() throws IOException {
        super();
    }

    private void receiveFile() throws IOException {
        // Defining the server socket
        getServerSoc() = new ServerSocket(port);

        // Defining the variable for reading the file
        int bytesRead;

        System.out.println("ServerTransferFile: Establishing socket");

        while (true) {
            // Connecting to the client socket
            Socket clientSoc = servSoc.accept();
            System.out.println("ServerTransferFile: Client connected");

            // Defining the socket output (inputStream) and the file output (outputstream)
            InputStream inStream = clientSoc.getInputStream();
            OutputStream output = new FileOutputStream(fileStorePath + fileName);

            // Read the write the file to the fileStorePath
            byte[] byteArray = new byte[1024];
            while ((bytesRead = inStream.read(byteArray)) != -1) {
                output.write(byteArray, 0, bytesRead);
            }

            //Start the MatLab processing thread
            startMatlab();

            // Close serversocket and outputstream, break the loop
            servSoc.close();
            output.close();
            break;
        }
    }

    private void startMatlab(){
        
    }
}
