package main;

import sendFile.FileSendCoordinator;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

public class FileDistribution {
    private static NodeList nodeList;

    public FileDistribution() {
        nodeList = new NodeList();
    }

    public static void distributeFile(File fileSend, int cpuUsage, int ramUsage) throws UnknownHostException, IOException, InterruptedException {
        NodeList list = new NodeList();
        Node selectedNode = new Node();

        list = nodeList.filterCpuRamUsage(nodeList, cpuUsage, ramUsage);
        selectedNode = list.getLeastNode(list, "CPU");

        FileSendCoordinator.sendFileOnce(fileSend, selectedNode.getServIpAddr());
    }
}
