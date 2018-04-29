package systemMonitor;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import com.sun.jna.*;
import org.slf4j.*;

public class CPUMonitor {
	
	public static void testGetProcessorNumber() { 
        SystemInfo si = new SystemInfo(); 
        HardwareAbstractionLayer hal = si.getHardware(); 
        System.out.println(hal.getProcessor().getLogicalProcessorCount());
    } 
}
