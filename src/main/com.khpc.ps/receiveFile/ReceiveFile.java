package receiveFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class ReceiveFile implements Runnable {

    // The field necessary for file storage
    private String fileStorePath;
    private String fileName;

    private int port;
    private Socket clientSoc;
    private ServerSocket servSoc;

    public ReceiveFile(String fileStorePath, int port) throws IOException {
        this.fileStorePath = fileStorePath;
        this.port = port;
    }

    public ReceiveFile() throws IOException {
        this("", 5000);
    }

    private void receiveFile() throws IOException, InterruptedException {
        // Defining the server socket
        servSoc = new ServerSocket(port);

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

            postReceiveOperation();

            // Close serversocket and outputstream, break the loop
            servSoc.close();
            output.close();
            break;
        }
    }

    public abstract void postReceiveOperation() throws IOException, InterruptedException;

    public String getFileStorePath() {
        return fileStorePath;
    }

    public void setfileStorePath(String fileStorePath) {
        this.fileStorePath = fileStorePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getClientSoc() {
        return clientSoc;
    }

    public void setClientSoc(Socket clientSoc) {
        this.clientSoc = clientSoc;
    }

    public ServerSocket getServSoc() {
        return servSoc;
    }

    public void setServSoc(ServerSocket servSoc) {
        this.servSoc = servSoc;
    }

    @Override
    public void run() {
        try {
            receiveFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
