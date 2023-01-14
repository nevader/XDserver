public class ClearVisitedNodes implements Broadcast<Void>{


    @Override
    public Void handle(Topology topology) {
        topology.clearVisitedNodes();
        System.out.println("CLEARED_LIST_OF_VISITED_NODES.]");
        topology.listVisitedNodes();
        return null;
    }



    @Override
    public MessageType getType() {
        return Broadcast.super.getType();
    }
}
