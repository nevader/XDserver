import java.io.Serializable;

public interface Message extends Serializable {
    public enum MessageType {
        REQUEST, BROADCAST, GET_MIN;

    }

    MessageType getType();
}