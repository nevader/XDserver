import java.net.Socket;

public class getStoredValue implements ClientRequest<Integer>{

    @Override
    public Integer handle(Storage storage) {
        return storage.getStoredValue();
    }

    @Override
    public Integer handle(Storage storage, Socket socket) {
        return null;
    }

    @Override
    public Integer getLol() {
        return null;
    }


    @Override
    public MessageType getType() {
        return ClientRequest.super.getType();
    }
}
