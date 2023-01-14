public class Client {
    private Communication comm = new Communication();

    public void start() throws Exception {
    }

    public void put(Integer key, Integer value, String gateway, int port) throws Exception {
        comm.execute(new PutClientRequest(key, value), gateway, port);
    }

    public Integer get(Integer key, String gateway, int port) throws Exception {
        return comm.execute(new GetClientRequest(key), gateway, port);
    }

    public static void main(String[] args) throws Exception {

        Client client = new Client();
        System.out.println(client.get(6, "localhost", 9000));

/*        String gateway = null;
        int port = 0;
        String identifier = null;
        String command = null;

        // Parameter scan loop
        for(int i=0; i<args.length; i++) {
            switch (args[i]) {
                case "-gateway":
                    String[] gatewayArray = args[++i].split(":");
                    gateway = gatewayArray[0];
                    port = Integer.parseInt(gatewayArray[1]);
                    break;
                case "-operation":
                    break;
                default:
                    if(command == null) {
                        command = args[i];
                    } else if(!"TERMINATE".equals(command)) {
                        command += " " + args[i];
                    }

            }
        }

        Client client = new Client();
        client.get(2, gateway, port);

        System.out.println("Read from the server: " + client.get(1, gateway, port));*/
    }


}