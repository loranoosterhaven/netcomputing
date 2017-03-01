import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server
{
    private int portNumber;

    // ip and socket used in webserver connection
    private String ip;
    private ServerSocket socket;

    Server(String ip, int portNumber) throws IOException
    {
        this.ip = ip;
        this.portNumber = portNumber;
        socket = new ServerSocket(portNumber);
    }

    void run() throws IOException {
        System.out.println("Starting server...");
        Socket incoming = socket.accept();
    }
}
