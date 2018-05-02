package main;

public class Node {
	private int cpuPercentage;
	private int ramPercentage;

	// Initialize cpu ran ram percentage load to 0
	public Node() {
		cpuPercentage = 0;
		ramPercentage = 0;
	}

	public void setCpuPercentage(int cpu) {
		cpuPercentage = cpu;
	}

	public void setRamPercentage(int ram) {
		ramPercentage = ram;
	}
	
	public int getCpuPercentage() {
		return cpuPercentage;
	}
	
	public int getRamPercentage() {
		return ramPercentage;
	}
}
