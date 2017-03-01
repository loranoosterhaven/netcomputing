import java.io.Console;
import java.io.IOException;

class Main
{
    public static void main(String[] args)
    {
        if (args.length < 1) {
            System.out.println("Usage: java server <portnumber>");
            return;
        }

        final int portNumber = Integer.parseInt(args[0]);

        try
        {
            Server server = new Server(portNumber);
            server.run();
        }

        catch (IOException e)
        {
            System.err.println("IOException occured" + e.getMessage());
            e.printStackTrace();
        }
    }
}
