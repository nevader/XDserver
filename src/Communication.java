import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
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

    public void listen(Storage storage) throws Exception {

        while (true) {

            System.out.println("server lisening");
            Socket socket = serverSocket.accept();
            System.out.println("Accepted client:");

            new Thread(() -> {
                while (true) {
                    try {
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                        Request<?> request = (Request<?>)in.readObject();
                        Object result = request.handle(storage);

                        out.writeObject(result);
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
}
