import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication {

    private ServerSocket serverSocket;

    public void start(int port) {
        while (true) {
            try {
                serverSocket = new ServerSocket(port);

                System.out.println("Server started.  " + port + " port.");
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void listen(Storage storage, Topology topology) throws Exception {

        while (true) {

            System.out.println("server lisening");
            Socket socket = serverSocket.accept();
            System.out.println("Accepted client:");

            new Thread(() -> {
                while (true) {
                    try {
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                        Message message = (Message)in.readObject();

                        if(message.getType() == Message.MessageType.REQUEST) {
                            Request<?> request = (Request<?>) message;
                            Object result = request.handle(storage);
                            out.writeObject(result);

                        }else if(message.getType() == Message.MessageType.BROADCAST){
                            Broadcast<?> broadcast = (Broadcast<?>) message;
                            broadcast.handle(topology);
                        }


                    }
                    catch (EOFException e1) {
                        break;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }

                }
            }).start();
        }
    }

    // Used by Clients
    public <R> R execute(Request<R> request, String gateway, int port) throws Exception {
        Socket socket = new Socket(gateway, port);
        System.out.println("Connected with");

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

        for (int i = 0; i < listOfNodes.size(); i++) {

            var dstAd = listOfNodes.get(i).getAddress();
            var dstPort = listOfNodes.get(i).getPort();

            Socket socket = null;
            try {
                socket = new Socket(dstAd, dstPort);
            } catch (IOException e) {
                throw new RuntimeException("Cannot connect to:" + dstPort);
            }
            System.out.println("Connected with");

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(request);

            socket.close();
            out.close();
            in.close();

        }
    }
}
