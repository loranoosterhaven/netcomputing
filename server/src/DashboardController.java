import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class DashboardController
{
    private int portNumber;

    // ip and socket used in webserver connection
    private String hostName;
    private Socket socket;

    DashboardController(String hostName, int portNumber) throws IOException
    {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    void run() throws IOException {
        System.out.println("Connecting to webserver...");
        socket = new Socket (hostName, portNumber);
        System.out.println("connected!");

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.write("Hello\n");
        out.flush();

        BufferedReader input =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = input.readLine();
        System.out.println(answer);

    }
}
