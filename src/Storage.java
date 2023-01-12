import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    private final Map<Integer, Integer> data = new ConcurrentHashMap<>();

    public void put(Integer key, Integer value) {

        System.out.println("PUT [key=" + key +", value=" + value + "]");

        data.put(key, value);
    }

    public Integer get(Integer key) {

        System.out.println("GET [key=" + key + "]");

        return data.get(key);

    }

}
