package server;

import shared.ClientRemoteController;
import shared.DeviceInfo;
import shared.RESTController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;


public class ClientRemoteControllerImpl extends UnicastRemoteObject implements ClientRemoteController
{
    private static final long serialVersionID = 1L;

    private HashMap<String, NodeInfo> nodesInfos;
    private ArrayList<String> shutdownList;
    private RESTController restController;

    public ClientRemoteControllerImpl( String dashboardIp, int dashboardPort ) throws RemoteException
    {
        super();
        nodesInfos = new HashMap<>();
        restController = new RESTController(dashboardIp, dashboardPort);
        shutdownList = new ArrayList<>();
    }

    /**
     * Register a new node
     * @return true if register succesfull, else false
     * @throws Exception
     */
    @Override
    public boolean registerNode() throws Exception {
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

        restController.registerNode(info);

        System.out.println("Registered client " + clientIp + ".");

        return true;
    }


    /**
     * Unregister a node, given that it is contained in the list
     * @return true if unregister succesfull, else false
     * @throws Exception
     */
    @Override
    public boolean unregisterNode() throws Exception {
        String clientIp = getClientHost();

        NodeInfo node = nodesInfos.get(clientIp);

        restController.unregisterNode(node);

        if( nodesInfos.remove(clientIp) == null ) {
            System.out.println("Attempted to unregister non-existing client " + clientIp + ".");
            return false;
        }

        System.out.println("Unregistered client " + clientIp + ".");
        return true;
    }

    /**
     * Update a client, given that it is contained in the node list
     * @param deviceInfo the new device info
     * @return true if update succesfull, false else
     * @throws Exception
     */
    @Override
    public boolean updateNode(DeviceInfo deviceInfo) throws Exception {
        String clientIp = getClientHost();
        NodeInfo node = nodesInfos.get(clientIp);

        if (node == null) {
            System.out.println("Attempted to update a non-existing client");
            return false;
        } else {
            node.setDeviceInfo(deviceInfo);
            node.setLastTick(System.currentTimeMillis());

            restController.updateNode(node);

            System.out.println("Update: " + deviceInfo.getHostname());
        }

        return true;
    }

    /**
     * Checks if a client should shutdown
     * @return true if client should shutdown, false else
     * @throws Exception
     */
    @Override
    public boolean shouldShutdown() throws Exception {
        String clientIp = getClientHost();
        return shutdownList.contains(clientIp);
    }

    /**
     * Handle client timeouts and shutdown
     * @throws Exception
     */
    public void syncNodes() throws Exception {
        while(true) {
            System.out.println("Checking all nodes");

            for (Iterator<Map.Entry<String, NodeInfo>> it = nodesInfos.entrySet().iterator(); it.hasNext();)
            {
                Map.Entry<String, NodeInfo> entry = it.next();

                String ip = entry.getValue().getIp();

                if( restController.checkShutdown(entry.getValue())) {
                    if (!shutdownList.contains(ip)) {
                        shutdownList.add(ip);
                    }
                }

                long diff = System.currentTimeMillis() - entry.getValue().getLastTick();
                if (diff > 20000) {
                    System.out.println("Timeout: removing node " + ip);
                    restController.unregisterNode(entry.getValue());
                    it.remove();
                }

            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
