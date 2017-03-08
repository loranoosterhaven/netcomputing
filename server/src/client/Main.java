package client;

import shared.ClientRemoteController;
import shared.DeviceInfo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;

/**
 * Created by hein on 8-3-17.
 */
public class Main {
    private static ClientRemoteController remoteController;

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, ServerNotActiveException, InterruptedException {
        Registry registry = LocateRegistry.getRegistry("localhost", 20202);
        remoteController = (ClientRemoteController) registry.lookup("ClientRemote");

        if( remoteController.registerNode() ) {
            Thread.sleep(10000);

            DeviceInfo dummyDevice = new DeviceInfo();
            dummyDevice.setHostname("TEST-DEVICE" + Math.random());
            remoteController.updateNode(dummyDevice);

            Thread.sleep(10000);

            remoteController.unregisterNode();
        }
    }
}
