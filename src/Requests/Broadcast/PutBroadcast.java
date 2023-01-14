import java.util.ArrayList;
import java.util.Set;

public class PutBroadcast implements Broadcast<Void>{

    Topology.Node nodeToAdd;

    public PutBroadcast(Topology.Node nodeToAdd) {
        this.nodeToAdd = nodeToAdd;
    }

    @Override
    public Void handle(Topology topology) {
        topology.addNode(nodeToAdd);
        System.out.println("ADDED_NEW_NODE [host=localhost, port=" + nodeToAdd.getPort() + "]");
        topology.listCurrentNodes();
        return null;
    }



    @Override
    public MessageType getType() {
        return Broadcast.super.getType();
    }
}
