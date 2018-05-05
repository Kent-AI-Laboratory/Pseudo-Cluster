package main;

public class Node {
	private int cpuPercentage;
	private int ramPercentage;
	private String servIpAddr;
	private String nodeName;

	// Initialize cpu ran ram percentage load to 0
	public Node(String servIpAddr, String nodeName) {
		cpuPercentage = 0;
		ramPercentage = 0;
		this.servIpAddr = servIpAddr;
		this.nodeName = nodeName;
	}

	public Node() {
		this("", "");
	}

	public void setCpuPercentage(int cpu) {
		this.cpuPercentage = cpu;
	}

	public void setRamPercentage(int ram) {
		this.ramPercentage = ram;
	}

	public void setServIpAddr(String servIpAddr) {
		this.servIpAddr = servIpAddr;
	}
	
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getCpuPercentage() {
		return cpuPercentage;
	}

	public int getRamPercentage() {
		return ramPercentage;
	}

	public String getServIpAddr() {
		return servIpAddr;
	}
	
	public String getNodeName() {
		return nodeName;
	}
}
