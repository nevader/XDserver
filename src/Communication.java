import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Communication implements Serializable {

    private ServerSocket serverSocket;

    public void start(int port, Storage storage) {
        while (true) {
            try {
                serverSocket = new ServerSocket(port);
                storage.setPort(port);
                System.out.println("SERVER_STARTED [host=localhost, port=" + serverSocket.getLocalPort() + "]");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void listen(Storage storage, Topology topology) throws Exception {

        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println("ACCEPTED_CONNECTION");

            new Thread(() -> {
                while (true) {
                    try {
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                        Message message = (Message) in.readObject();

                        if (message.getType() == Message.MessageType.REQUEST) {

                            System.out.println("\nACCEPTED_CLIENT_REQUEST");
                            ClientRequest<?> request = (ClientRequest<?>) message;
                            Object result = request.handle(storage);

                            if (result == null) {
                                broadcastTopology(new NodeVisitedAdd(new Topology.Node("localhost", serverSocket.getLocalPort())),
                                        topology);
                                var value = askForValue(topology, request);
                                System.out.println("\nRETURNING_REQUEST [value=" + value + "]");
                                out.writeObject(Objects.requireNonNullElse(value, "ERROR"));
                                topology.clearVisitedNodes();

                            } else {
                                out.writeObject(result);
                            }

                        } else if (message.getType() == Message.MessageType.BROADCAST) {

                            System.out.println("\nACCEPTED_BROADCAST_REQUEST");
                            Broadcast<?> broadcast = (Broadcast<?>) message;
                            broadcast.handle(topology);

                        }

                    } catch (EOFException e1) {
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }

                }
            }).start();
        }
    }

    public <R> R askForValue(Topology topology, ClientRequest<?> request) throws Exception {

        var listOfNodes = topology.getTopology().get(serverSocket.getLocalPort());

        for (Topology.Node current : listOfNodes) {
            if (!topology.getVisitedNodes().contains(current)) {
                return (R) execute(request, "localhost", current.getPort());
            }
        }
        return null;
    }


    // Used by Clients
    public <R> R execute(ClientRequest<R> request, String gateway, int port) throws Exception {
        Socket socket = new Socket(gateway, port);
        System.out.println("\nCONNECTED_WITH [host=localhost, port=" + port + "]");

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        out.writeObject(request);

        var x = (R)in.readObject();

        socket.close();
        out.close();
        in.close();

        return x;
    }

    public <R> void broadcastTopology(Broadcast<R> request, Topology topology) throws IOException {

        //sprawdza kazdego noda i daje mu znac ze sie z nim polaczyl
        var listOfNodes = topology.getTopology().get(serverSocket.getLocalPort());

        for (Topology.Node current : listOfNodes) {
            var dstAd = current.getAddress();
            var dstPort = current.getPort();
            Socket socket;
            try {
                socket = new Socket(dstAd, dstPort);
                System.out.println(request.getClass() + "\nSENDING_BROADCAST to [host=localhost, port=" + dstPort + "]");
            } catch (IOException e) {
                throw new RuntimeException("\nCannot connect to:" + dstPort);
            }


            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(request);

            socket.close();
            out.close();
            in.close();
        }

    }
}
