import java.net.Socket;

public class findKey implements ClientRequest <String>{

    private final Integer key;

    public findKey(Integer key) {
        this.key = key;
    }

    @Override
    public String handle(Storage storage) {
        return storage.findKey(key);
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
