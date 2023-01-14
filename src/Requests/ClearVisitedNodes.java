public class ClearVisitedNodes implements Broadcast<Void>{


    @Override
    public Void handle(Topology topology) {
        topology.clearVisitedNodes();
        return null;
    }

    @Override
    public MessageType getType() {
        return Broadcast.super.getType();
    }
}
