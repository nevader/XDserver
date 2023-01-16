import java.net.Socket;
import java.util.ArrayList;

public class getStoredValue implements ClientRequest<Integer>{

    @Override
    public Integer handle(Storage storage) {
        return storage.getStoredValue();
    }

    @Override
    public Integer handle(Storage storage, ArrayList<Integer> arrayList) {
        return null;
    }


    @Override
    public MessageType getType() {
        return ClientRequest.super.getType();
    }
}
