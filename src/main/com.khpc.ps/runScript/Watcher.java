package runScript;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;

public class Watcher implements Runnable{

    private String watchPath;

    public Watcher(String watchPath){
        this.watchPath = watchPath;
    }

    private void initiateWatching(String watchPath) throws IOException, InterruptedException{
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(watchPath);

        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        WatchKey watchKey;
        while((watchKey = watchService.take()) != null){
            for(WatchEvent<?> event : watchKey.pollEvents()){
                System.out.println("Event kind: " + event.kind() + ". File Affected: " + event.context() + ".");
            }
        }
    }

    public String getWatchPath(){
        return watchPath;
    }

    public void setWatchPath(String watchPath){
        this.watchPath = watchPath;
    }

    public void run(){
        try{
            initiateWatching(watchPath);
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
