import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public interface ClientRequest<R> extends Message {


    R handle(Storage storage);
    R handle(Storage storage, Socket socket);
    R getLol();

    @Override
    default MessageType getType() {
        return MessageType.REQUEST;
    }
}