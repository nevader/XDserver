import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Topology {

    private int hostPort;
    private String minValue;
    private final Map<Integer, HashSet<Node>> topology = new ConcurrentHashMap<>();
    private final HashSet<Node> visitedNodes = new HashSet<>();

    public void setMinValue(String minValue) {
        System.out.println("Setted min value to " + minValue);
        this.minValue = minValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void join(HashSet<Node> connectedNodes) {
            topology.put(hostPort, connectedNodes);
    }
    public void addVisitedNode(Node nodeToAdd) {
        visitedNodes.add(nodeToAdd);
    }
    public void clearVisitedNodes() {
        System.out.println("CLEARED_LIST_OF_VISITED_NODES.]");
        visitedNodes.clear();


    }

    public void addNode(Node nodeToAdd) {

        Set<Node> nodeList = topology.get(hostPort);
        nodeList.add(nodeToAdd);
    }

    public void listCurrentNodes() {
        Set<Node> nodeList = topology.get(hostPort);
        System.out.println("CONNECTED_NODES:");
        for (Node current : nodeList) {
            System.out.println("- [host=localhost, port=" + current.getPort() + "]");
        }
    }
    public void listVisitedNodes() {
        System.out.println("VISITED_NODES:");
        for (Node current : visitedNodes) {
            System.out.println("- [host=localhost, port=" + current.getPort() + "]");
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
