package main;

import java.util.ArrayList;
import java.util.List;

public class NodeList {
	private List<Node> nodeList;

	public NodeList() {
		nodeList = new ArrayList<Node>();
	}

	public void addNode(Node nodeIn) {
		nodeList.add(nodeIn);
	}

	public boolean deleteNode(String servIpAddrorNodeName) {
		for (Node node : nodeList) {
			if (node.getServIpAddr() == servIpAddrorNodeName) {
				nodeList.remove(node);
				return true;
			}
		}

		for (Node node : nodeList) {
			if (node.getNodeName() == servIpAddrorNodeName) {
				nodeList.remove(node);
				return true;
			}
		}

		return false;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public List<Node> filterUsage(List<Node> originList, String type, int usageLimit) {

		ArrayList<Node> resultList = new ArrayList<Node>();

		if (type == "CPU" || type == "cpu") {
			for (Node node : originList) {
				if (node.getCpuPercentage() <= usageLimit) {
					resultList.add(node);
				}
			}
		}

		if (type == "RAM" || type == "ram") {
			for (Node node : originList) {
				if (node.getRamPercentage() <= usageLimit) {
					resultList.add(node);
				}
			}
		}
		
		return resultList;
	}

	public List<Node> filterCpuRamUsage(List<Node> originList, int cpuUsageLimit, int ramUsageLimit) {
		ArrayList<Node> resultList;
		resultList = (ArrayList<Node>) filterUsage(nodeList, "CPU", cpuUsageLimit);
		resultList = (ArrayList<Node>) filterUsage(resultList, "RAM", ramUsageLimit);

		return resultList;
	}

	public Node getLeastNode(List<Node> originList, String type) {
		int leastUsage = 100;
		Node leastNode = null;

		if (type == "CPU" || type == "cpu") {
			for (Node node : originList) {
				if (node.getCpuPercentage() < leastUsage) {
					leastUsage = node.getCpuPercentage();
					leastNode = node;
				}
			}
		}

		if (type == "RAM" || type == "ram") {
			for (Node node : originList) {
				if (node.getRamPercentage() < leastUsage) {
					leastUsage = node.getRamPercentage();
					leastNode = node;
				}
			}
		}

		return leastNode;
	}
}
