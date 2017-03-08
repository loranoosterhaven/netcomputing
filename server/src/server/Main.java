package server;

import shared.ClientRemoteController;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

class Main
{
    public static void main(String[] args) throws RemoteException, InterruptedException {
//        if (args.length < 2) {
//            System.out.println("Usage: java server <ip> <portnumber>");
//            System.exit(1);
//        }
//
//        // port and ip of the webserver
//        final String ip = args[0];
//        final int portNumber = Integer.parseInt(args[1]);
//
//        try
//        {
//            // Start server
//            DashboardController server = new DashboardController(ip, portNumber);
//            server.run();
//        }
//
//        catch (IOException e)
//        {
//            System.err.println("IOException occured:\n" + e.getMessage());
//            e.printStackTrace();
//        }

        // Create client listener.
        try {
            ClientRemoteControllerImpl cc = new ClientRemoteControllerImpl();
            UnicastRemoteObject.unexportObject(cc, true);
            ClientRemoteController stub = (ClientRemoteController) UnicastRemoteObject.exportObject(cc,  0);

            Registry registry = LocateRegistry.createRegistry(20202);
            registry.bind("ClientRemote", stub);

            System.out.println("Server is running!");

            cc.startTimeoutChecker();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
