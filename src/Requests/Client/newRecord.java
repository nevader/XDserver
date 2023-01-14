import java.net.Socket;

public class newRecord implements ClientRequest<String>{

    private final Integer key;
    private final Integer value;

    public newRecord(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String handle(Storage storage) {
        storage.put(key, value);
        return "OK";
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
