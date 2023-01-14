import java.net.Socket;

public class getValue implements ClientRequest<String> {

    private final Integer key;

    public getValue(Integer key) {
        this.key = key;
    }

    @Override
    public String handle(Storage storage) {
        return storage.getValueAsString(key);
    }

    @Override
    public String handle(Storage storage, Socket socket) {
        return null;
    }

    @Override
    public String getLol() {
        return null;
    }

    @Override
    public MessageType getType() {
        return ClientRequest.super.getType();
    }
}
