public class NodeVisitedAdd implements Broadcast<Void> {


    Integer port;
    String address;

    public NodeVisitedAdd(Integer port, String address) {
        this.port = port;
        this.address = address;
    }

    @Override
    public Void handle(Topology topology) {
        topology.addVisitedNode(port, address);
        return null;
    }

}
