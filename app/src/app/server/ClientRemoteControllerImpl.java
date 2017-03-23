package app.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import app.shared.ClientRemoteController;
import app.shared.DeviceInfo;
import app.shared.Johan;

import java.net.InetAddress;
import java.rmi.RemoteException;
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
    private ArrayList<String> shutdownList;

    private Johan johan;

    public ClientRemoteControllerImpl( String dashboardIp, int dashboardPort ) throws RemoteException
    {
        super();
        nodesInfos = new HashMap<>();
        johan = new Johan(dashboardIp, dashboardPort);
        shutdownList = new ArrayList<>();
    }

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

        johan.registerNode(info);

        System.out.println("Registered client " + clientIp + ".");

        return true;
    }

    @Override
    public boolean unregisterNode() throws Exception {
        String clientIp = getClientHost();

        NodeInfo node = nodesInfos.get(clientIp);

        johan.unregisterNode(node);

        if( nodesInfos.remove(clientIp) == null ) {
            System.out.println("Attempted to unregister non-existing client " + clientIp + ".");
            return false;
        }

        System.out.println("Unregistered client " + clientIp + ".");
        return true;
    }

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

            johan.updateNode(node);

            System.out.println("Update: " + deviceInfo.getHostname());
        }

        return true;
    }

    @Override
    public boolean shouldShutdown() throws Exception {
        String clientIp = getClientHost();
        return shutdownList.contains(clientIp);
    }

    public void syncNodes() throws Exception {
        while(true) {
            System.out.println("Checking all nodes");

            for (Iterator<Map.Entry<String, NodeInfo>> it = nodesInfos.entrySet().iterator(); it.hasNext();)
            {
                Map.Entry<String, NodeInfo> entry = it.next();

                String ip = entry.getValue().getIp();

                if( johan.checkShutdown(entry.getValue())) {
                    if (!shutdownList.contains(ip)) {
                        shutdownList.add(ip);
                    }
                }

                long diff = System.currentTimeMillis() - entry.getValue().getLastTick();
                if (diff > 20000) {
                    System.out.println("Timeout: removing node " + ip);
                    johan.unregisterNode(entry.getValue());
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
