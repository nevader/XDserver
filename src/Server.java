public class Server {

    private final Communication comm = new Communication();
    private final Storage storage = new Storage();

    public void start() throws Exception {

        comm.start(9000);
        comm.listen(storage);
    }

    public static void main(String[] args) throws Exception {
        new Server().start();
    }
}
