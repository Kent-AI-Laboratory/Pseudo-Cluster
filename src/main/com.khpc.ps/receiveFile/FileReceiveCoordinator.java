package receiveFile;

import java.io.IOException;

public abstract class FileReceiveCoordinator implements Runnable {

    private String fileStorePath;

    public abstract void startReceiveServer() throws IOException;

    public String getFileStorePath() {
        return fileStorePath;
    }

    public void setFileStorePath(String fileStorePath) {
        this.fileStorePath = fileStorePath;
    }

    @Override
    public void run() {
        try {
            startReceiveServer();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
