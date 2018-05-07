package runScript;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
 
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.watchPath;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

 
public class RunFile {
	
	private Path watchPath;
	private String opSystem;
	private String fileName;
	
	public RunFile(String path,String name,String opSystem)
	{
		watchPath = Paths.get(path);
		fileName=name;
		this.opSystem=opSystem;
	}
	
	public void deleteFile()
	{
		File file = new File(watchPath+fileName);     
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
	}
	
	public static void deleteFile(String filePath)
	{
		File file = new File(filePath);     
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
	}
	
	public void detectAndRun() {
		// Sanity check - Check if watchPath is a folder
		try {
			Boolean isFolder = (Boolean) Files.getAttribute(watchPath,
					"basic:isDirectory", NOFOLLOW_LINKS);
			if (!isFolder) {
				throw new IllegalArgumentException("Path: " + watchPath + " is not a folder");
			}
		} catch (IOException ioe) {
			// Folder does not exists
			ioe.printStackTrace();
		}
		
		System.out.println("Watching watchPath: " + watchPath);
		
		// We obtain the file system of the watchPath
		FileSystem fs = watchPath.getFileSystem ();
		
		// We create the new WatchService using the new try() block
		try(WatchService service = fs.newWatchService()) {
			
			// We register the watchPath to the service
			// We watch for creation events
			watchPath.register(service, ENTRY_CREATE);
			
			// Start the infinite polling loop
			WatchKey key = null;
			while(true) {
				key = service.take();
				
				// Dequeueing events
				Kind<?> kind = null;
				for(WatchEvent<?> watchEvent : key.pollEvents()) {
					// Get the type of the event
					kind = watchEvent.kind();
					if (OVERFLOW == kind) {
						continue; //loop
					} else if (ENTRY_CREATE == kind) {
						// A new watchPath was created 
						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						// Output
						System.out.println("New watchPath created: " + newPath);
						
				        Process p=null;
				        try {
				            p = Runtime.getRuntime().exec("sh -c 'cd "+ watchPath+"&& ProgToExecute'");
				            //Documents/GitHub/Subspace-Learning/Classical-Algorithm
				            p.getErrorStream();  
				            p.waitFor();
				            
				            p = Runtime.getRuntime().exec("matlab -r "+fileName);
				            p.getErrorStream();  
				            p.waitFor();
				            return;
				            
				        } catch (Exception e) {
				        	e.printStackTrace();  
				        	System.out.println("ERROR.RUNNING.CMD");  }
				        finally{
				        	p.destroy();
				        }	 
				        
					}
				}
				
				if(!key.reset()) {
					break; //loop
				}
			}
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		
	}
 
	/* The following block is for testing purpose only
	public static void main(String[] args) throws IOException,
			InterruptedException {
		// Folder we are going to watch
		RunFile a = new RunFile("//Users//alexanderliao//Documents//test","testing.m","MacOS");
		//Path folder = Paths.get("//home//alexanderliao//Documents//test");
		a.detectAndRun();
	}
	*/
}
