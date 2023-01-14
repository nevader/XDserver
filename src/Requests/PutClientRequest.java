public class PutClientRequest implements ClientRequest<Void> {

    private Integer key;
    private Integer value;

    public PutClientRequest(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Void handle(Storage storage) {

        storage.put(key, value);

        return null;
    }
    
}
