import java.util.List;

public class DataRequest implements ClientRequest<Integer> {
    private final Integer key;
    private final List<String> visitedNodes;
    public DataRequest(Integer key, List<String> visitedNodes) {
        this.key = key;
        this.visitedNodes = visitedNodes;
    }
    public Integer getKey() {
        return key;
    }
    public List<String> getVisitedNodes() {
        return visitedNodes;
    }
    @Override
    public Integer handle(Storage storage) {
        return storage.get(key);
    }
}