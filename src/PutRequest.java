public class PutRequest implements Request<Void> {

    private Integer key;
    private Integer value;

    public PutRequest(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Void handle(Storage storage) {

        storage.put(key, value);

        return null;
    }
}
