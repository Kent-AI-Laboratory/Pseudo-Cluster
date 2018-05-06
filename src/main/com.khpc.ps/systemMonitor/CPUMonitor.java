package systemMonitor;

import java.io.File;
import java.io.PrintWriter;

public class CPUMonitor {
	public static void main(String args[]) throws Exception {
		PrintWriter writer = new PrintWriter(file);
		writer.println("wmic cpu get loadpercentage>result.txt");
		writer.close();
		
		Process process = Runtime.getRuntime().exec("cmd /c start " + file.getAbsolutePath());
		
	}
}