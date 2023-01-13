import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Topology {

    private int hostPort;
    private final Map<Integer, ArrayList<Node>> topology = new ConcurrentHashMap<>();


    public void join(ArrayList<Node> connectedNodes) {
            topology.put(hostPort, connectedNodes);

    }

    public void addNode(Node nodeToAdd) {

        System.out.println("Added new node to network.");
        ArrayList<Node> nodeList = topology.get(hostPort);
        nodeList.add(nodeToAdd);


        System.out.println("Current nodes:");
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println(nodeList.get(i).port);
        }
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public int getHostPort() {
        return hostPort;
    }

    public Map<Integer, ArrayList<Node>> getTopology() {
        return topology;
    }


    public static class Node implements Serializable {
        private final int port;
        private final String address;

        public Node(String address, int port) {
            this.port = port;
            this.address = address;
        }

        public int getPort() {
            return port;
        }

        public String getAddress() {
            return address;
        }
    }
}
