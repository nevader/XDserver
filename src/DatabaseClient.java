import java.util.*;

public class DatabaseClient {
    private Communication comm = new Communication();

    public void start(String command, String gateway, int port, Integer key, Integer value) throws Exception {

        switch (command) {
            case "get-max":
                getMinMax(gateway, port, "max");
                break;
            case "get-min":
                getMinMax(gateway, port, "min");
                break;
            case "set-value":
                setValue(key, value, gateway, port);
                break;
            case "get-value":
                getValue(key, gateway, port);
                break;
            case "find-key":
                findKey(key, gateway, port);
                break;
            case "new-record":
                newRecord(key, value, gateway, port);
                break;
            default:
                System.out.println("lol");
        }
    }

    public void setValue(Integer key, Integer value, String gateway, int port) throws Exception {
        var result = comm.execute(new setValue(key, value), gateway, port);
        System.out.println(result);
    }

    public void getValue(Integer key, String gateway, int port) throws Exception {
        var result = comm.execute(new getValue(key), gateway, port);
        System.out.println(result);
    }

    public void findKey(Integer key, String gateway, int port) throws Exception {
        var result = comm.execute(new findKey(key), gateway, port);
        System.out.println(result);
    }

    public void newRecord(Integer key, Integer value, String gateway, int port) throws Exception {
        var result = comm.execute(new newRecord(key, value), gateway, port);
        System.out.println(result);
    }

    public void getMinMax(String gateway, int port, String maxMin) throws Exception {
        ArrayList<Integer> lista = new ArrayList<>();
        lista = comm.execute(new getMinMax(lista), gateway, port);

        switch (maxMin) {
            case "max":
                System.out.println(Collections.max(lista));
                break;
            case "min":
                System.out.println(Collections.min(lista));
                break;
        }
    }

    public static void main(String[] args) throws Exception {

         String operation = "default";
         String key = "1";
         String value = "1";
         String address = "default";
         String port = "1";

        // Parameter scan loop
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("-gateway")) {
                String[] addressIp = args[i + 1].split(":");
                address = addressIp[0];
                port = addressIp[1];
            } else if (arg.equals("-operation")) {
                operation = args[i + 1];
                if (operation.equals("set-value") || operation.equals("new-record")) {
                    String[] keyValue = args[i + 2].split(":");
                    key = keyValue[0];
                    value = keyValue[1];
                } else if (operation.equals("get-value") || operation.equals("find-key")) {
                    key = args[i + 2];
                }
            }
        }

        new DatabaseClient().start(operation, address, Integer.parseInt(port), Integer.parseInt(key), Integer.parseInt(value));
    }


}