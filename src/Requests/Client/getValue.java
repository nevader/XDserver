import java.net.Socket;
import java.util.ArrayList;

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
    public String handle(Storage storage, ArrayList<Integer> arrayList) {
        return null;
    }


    @Override
    public MessageType getType() {
        return ClientRequest.super.getType();
    }
}
