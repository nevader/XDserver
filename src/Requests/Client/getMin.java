import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class getMin implements ClientRequest<String> {
    ConcurrentHashMap<String, String> lol = new ConcurrentHashMap<>();

    @Override
    public String handle(Storage storage) {
        lol.put(storage.getValueAsStr(), storage.getValueAsStr());
        return null;
    }

    @Override
    public String handle(Storage storage, Socket socket) {
        System.out.println("socket in getmin: " + socket.getLocalPort());
        return null;
    }

    @Override
    public String getLol() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String value : lol.values()) {
            stringBuilder.append(value).append(":");
        }
        return stringBuilder.toString();
    }


    @Override
    public MessageType getType() {
        return ClientRequest.super.getType();
    }
}