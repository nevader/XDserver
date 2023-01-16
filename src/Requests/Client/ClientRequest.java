import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public interface ClientRequest<R> extends Message {


    R handle(Storage storage);
    R handle(Storage storage, ArrayList<Integer> arrayList);


    @Override
    default MessageType getType() {
        return MessageType.REQUEST;
    }
}