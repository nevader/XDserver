public class Client {
    private Communication comm = new Communication();

    public void start() throws Exception {
    }

    public String setValue(Integer key, Integer value, String gateway, int port) throws Exception {
        return comm.execute(new setValue(key, value), gateway, port);
    }

    public String getValue(Integer key, String gateway, int port) throws Exception {
        return comm.execute(new getValue(key), gateway, port);
    }

    public String findKey(Integer key, String gateway, int port) throws Exception {
        return comm.execute(new findKey(key), gateway, port);
    }

    public String newRecord(Integer key, Integer value, String gateway, int port) throws Exception {
        return comm.execute(new newRecord(key, value), gateway, port);
    }

    public String getMin(String gateway, int port) throws Exception {
          return comm.execute(new getMin(), gateway, port);
    }

    public static void main(String[] args) throws Exception {

        String gateway = null;
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
        System.out.println(client.setValue(7, 5, "localhost", 9000));



    }


}