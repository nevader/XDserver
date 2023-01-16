import java.net.Socket;
import java.util.ArrayList;

public class setValue implements ClientRequest <String>{

    private Integer key;
    private Integer value;

    public setValue(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String handle(Storage storage) {
        return storage.replaceValue(key, value);
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
