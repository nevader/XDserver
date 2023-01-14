import java.io.Serializable;

public interface ServerRequest extends Message {

    void handle(Topology topology);
    @Override
    default MessageType getType() {
        return MessageType.BROADCAST;
    }
}
