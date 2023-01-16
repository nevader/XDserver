import java.io.Serializable;

public interface Message extends Serializable {
    public enum MessageType {
        REQUEST, BROADCAST, GET_MIN_MAX;

    }

    MessageType getType();
}