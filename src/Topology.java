import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Topology {

    private int hostPort;
    private final Map<Integer, HashSet<Node>> topology = new ConcurrentHashMap<>();
    private final HashSet<Node> visitedNodes = new HashSet<>();


    public void join(HashSet<Node> connectedNodes) {
            topology.put(hostPort, connectedNodes);
    }
    public void addVisitedNode(int host, String address) {
        visitedNodes.add(new Node(address, host));
    }
    public void clearVisitedNodes() {
        visitedNodes.clear();
    }

    public void addNode(Node nodeToAdd) {

        System.out.println("Added new node to network.");
        Set<Node> nodeList = topology.get(hostPort);
        nodeList.add(nodeToAdd);


        System.out.println("Current nodes:");

        for (Node current : nodeList) {
            System.out.println(current.getPort());
        }
    }
    public HashSet<Node> getVisitedNodes() {
        return visitedNodes;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public int getHostPort() {
        return hostPort;
    }

    public Map<Integer, HashSet<Node>> getTopology() {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (port != node.port) return false;
            return Objects.equals(address, node.address);
        }

        @Override
        public int hashCode() {
            int result = port;
            result = 31 * result + (address != null ? address.hashCode() : 0);
            return result;
        }
    }
}
