public class GetRequest implements Request<Integer> {
    private Integer key;

    public GetRequest(Integer key) {
        this.key = key;

    }
    @Override
    public Integer handle(Storage storage) {

        return storage.get(key);
    }
}
