package server;

import shared.ClientRemoteController;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

class Main
{
    public static void main(String[] args) throws RemoteException, InterruptedException {
        /*if (args.length < 4) {
            System.out.println("Usage: java server <ip> <portnumber> <dashboardip> <dashboardport>");
            System.exit(1);
        }*/

        // port and ip of the server
        final String ip = "192.168.1.8";//args[0];
        final int portNumber = 20202;//Integer.parseInt(args[1]);

        final String dashboardIp = "192.168.1.2";//args[2];
        final int dashboardPort = 8080;//Integer.parseInt(args[3]);

        System.setProperty("java.rmi.server.hostname", ip);

        // Create client listener.
        try {
            ClientRemoteControllerImpl cc = new ClientRemoteControllerImpl(dashboardIp,dashboardPort);
            UnicastRemoteObject.unexportObject(cc, true);
            ClientRemoteController stub = (ClientRemoteController) UnicastRemoteObject.exportObject(cc,  0);

            Registry registry = LocateRegistry.createRegistry(portNumber);
            registry.bind("ClientRemote", stub);

            System.out.println("Server is running on " + ip + ":" + portNumber);

            cc.syncNodes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
