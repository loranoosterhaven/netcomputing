package server;

import shared.ClientRemoteController;
import shared.DeviceInfo;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * Created by hein on 8-3-17.
 */
public class ClientRemoteControllerImpl extends UnicastRemoteObject implements ClientRemoteController
{
    private static final long serialVersionID = 1L;

    private HashMap<String, NodeInfo> nodesInfos;

    public ClientRemoteControllerImpl() throws RemoteException
    {
        super();
        nodesInfos = new HashMap<>();
    }

    @Override
    public boolean registerNode() throws RemoteException, ServerNotActiveException {
        String clientIp = getClientHost();
        if( nodesInfos.containsKey(clientIp) )
        {
            System.out.println("Attempted to register client " + clientIp + " twice.");
            return false;
        }

        NodeInfo info = new NodeInfo();
        info.setIp(clientIp);
        info.setLastTick(System.currentTimeMillis());
        nodesInfos.put(clientIp,info);

        System.out.println("Registered client " + clientIp + ".");
        return true;
    }

    @Override
    public boolean unregisterNode() throws RemoteException, ServerNotActiveException {
        String clientIp = getClientHost();
        if( nodesInfos.remove(clientIp) == null ) {
            System.out.println("Attempted to unregister non-existing client " + clientIp + ".");
            return false;
        }

        System.out.println("Unregistered client " + clientIp + ".");
        return true;
    }

    @Override
    public boolean updateNode(DeviceInfo deviceInfo) throws RemoteException, ServerNotActiveException {
        String clientIp = getClientHost();
        NodeInfo node = nodesInfos.get(clientIp);

        if (node == null) {
            System.out.println("Attempted to update a non-existing client");
            return false;
        } else {
            node.setDeviceInfo(deviceInfo);
            node.setLastTick(System.currentTimeMillis());
            System.out.println("Update: " + deviceInfo.getHostname());
        }

        return true;
    }

    public void startTimeoutChecker() {
        while(true) {
            System.out.println("Checking all nodes");

            for (Iterator<Map.Entry<String, NodeInfo>> it = nodesInfos.entrySet().iterator(); it.hasNext();)
            {
                Map.Entry<String, NodeInfo> entry = it.next();
                long diff = System.currentTimeMillis() - entry.getValue().getLastTick();
                if (diff > 20000) {
                    System.out.println("Timeout: removing node " + entry.getValue().getIp());
                    it.remove();
                }
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
