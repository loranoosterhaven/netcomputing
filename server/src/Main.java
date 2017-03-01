import java.io.Console;
import java.io.IOException;

class Main
{
    public static void main(String[] args)
    {
        if (args.length < 2) {
            System.out.println("Usage: java server <ip> <portnumber>");
            System.exit(1);
        }

        // port and ip of the webserver
        final int portNumber = Integer.parseInt(args[0]);
        final String ip = args[1];

        try
        {
            // Start server
            Server server = new Server(ip, portNumber);
            server.run();
        }

        catch (IOException e)
        {
            System.err.println("IOException occured:\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
