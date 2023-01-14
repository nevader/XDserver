public class GetClientRequest implements ClientRequest<Integer> {
    private Integer key;

    public GetClientRequest(Integer key) {
        this.key = key;

    }
    @Override
    public Integer handle(Storage storage) {

        return storage.get(key);
    }
}
