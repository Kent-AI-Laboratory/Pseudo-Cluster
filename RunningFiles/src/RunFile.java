import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
 
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class RunFile {
 
	public static void watchDirectoryPath(Path path) {
		// Sanity check - Check if path is a folder
		try {
			Boolean isFolder = (Boolean) Files.getAttribute(path,
					"basic:isDirectory", NOFOLLOW_LINKS);
			if (!isFolder) {
				throw new IllegalArgumentException("Path: " + path + " is not a folder");
			}
		} catch (IOException ioe) {
			// Folder does not exists
			ioe.printStackTrace();
		}
		
		System.out.println("Watching path: " + path);
		
		// We obtain the file system of the Path
		FileSystem fs = path.getFileSystem ();
		
		// We create the new WatchService using the new try() block
		try(WatchService service = fs.newWatchService()) {
			
			// We register the path to the service
			// We watch for creation events
			path.register(service, ENTRY_CREATE);
			
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
						// A new Path was created 
						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						// Output
						System.out.println("New path created: " + newPath);
						
						String s;
				        Process p=null;
				        try {
				            p = Runtime.getRuntime().exec("sh -c 'cd ~/Documents/GitHub/Subspace-Learning/Classical-Algorithm && ProgToExecute'");
				            //Documents/GitHub/Subspace-Learning/Classical-Algorithm
				            p.getErrorStream();  
				            p.waitFor();
				            
				            p = Runtime.getRuntime().exec("matlab -r bitTensor");
				            p.getErrorStream();  
				            p.waitFor();
				            
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
 
	public static void main(String[] args) throws IOException,
			InterruptedException {
		// Folder we are going to watch
		Path folder = Paths.get("//home//alexanderliao//Documents//test");
		watchDirectoryPath(folder);
	}
}
