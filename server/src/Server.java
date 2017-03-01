import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


class Server
{
    private int portNumber;
    private ServerSocket socket;

    Server(int portNumber) throws IOException
    {
        this.portNumber = portNumber;
        socket = new ServerSocket(portNumber);
    }

    void run() throws IOException {
        System.out.println("Starting server...");
        Socket incoming = socket.accept();
    }
}
