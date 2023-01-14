public class NodeVisitedAdd implements Broadcast<Void> {


    Topology.Node nodeToAdd;

    public NodeVisitedAdd(Topology.Node nodeToAdd) {
        this.nodeToAdd = nodeToAdd;
    }

    @Override
    public Void handle(Topology topology) {
        topology.addVisitedNode(nodeToAdd);
        System.out.println("ADDED_NEW_NODE_TO_VISITED_LIST [host=localhost, port=" + nodeToAdd.getPort() + "]");
        topology.listVisitedNodes();
        return null;
    }



    @Override
    public MessageType getType() {
        return Broadcast.super.getType();
    }

}
