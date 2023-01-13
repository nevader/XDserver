import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    private final Map<Integer, Integer> data = new ConcurrentHashMap<>();

    public void put(Integer key, Integer value) {

        System.out.println("PUT [key=" + key +", value=" + value + "]");

        data.put(key, value);
    }

    public Integer get(Integer key) {

        System.out.println("getting: " + key);
        System.out.println("returning: " + data.get(key));
        return data.get(key);

    }

}
