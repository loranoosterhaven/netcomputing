import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server
{
    private int portNumber;

    // ip and socket used in webserver connection
    private String hostName;
    private Socket socket;

    Server(String hostName, int portNumber) throws IOException
    {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    void run() throws IOException {
        System.out.print("Connecting to webserver...");
        socket = new Socket (hostName, portNumber);
        System.out.println("connected!");

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.write("Hello");
    }
}
