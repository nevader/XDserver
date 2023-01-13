import java.util.ArrayList;

public class PutBroadcast implements Broadcast<Void>{

    Topology.Node nodeToAdd;

    public PutBroadcast(Topology.Node nodeToAdd) {
        this.nodeToAdd = nodeToAdd;
    }

    @Override
    public Void handle(Topology topology) {
        topology.addNode(nodeToAdd);
        return null;
    }
}
