package client;

import server.Johan;
import server.NodeInfo;
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

    public static void main(String[] args) throws Exception
    {

        Registry registry = LocateRegistry.getRegistry("localhost", 20202);
        remoteController = (ClientRemoteController) registry.lookup("ClientRemote");

        if( remoteController.registerNode() ) {
            Thread.sleep(1002);

            DeviceInfo dummyDevice = new DeviceInfo();
            dummyDevice.setHostname("TEST-DEVICE");

            while( true  ) {
                dummyDevice.getSystemInfo().setCpu(Math.random());
                remoteController.updateNode(dummyDevice);

                Thread.sleep(1000);
            }
        }
    }
}
