package server;

import java.rmi.RemoteException;

/**
 * Created by hein on 8-3-17.
 */
public class ClientControllerImpl implements ClientController {
    @Override
    public boolean sendObject(DeviceInfo deviceInfo) throws RemoteException {
        System.out.println("Received: " + deviceInfo.getHostname());
        return false;
    }
}
