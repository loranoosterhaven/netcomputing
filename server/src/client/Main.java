package client;

import shared.*;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

/**
 * Created by hein on 8-3-17.
 */
public class Main {
    private static ClientRemoteController remoteController;

    static void setProcessList(DeviceInfo info)
    {
        int count = 3;
        info.getProcessInfo().clear();
        for (int i = 0; i < count; i++) {
            ProcessInfo pinfo = new ProcessInfo();
            pinfo.setPid(i);
            pinfo.setName("process");
            pinfo.setPath("/");
            pinfo.setUptime(355);
            info.getProcessInfo().add(pinfo);

            for (int j = 0; j < 2; j++) {
                ThreadInfo thrInfo = new ThreadInfo();
                thrInfo.setUptime(200);
                thrInfo.setId(3);
                pinfo.getThreadInfo().add(thrInfo);
            }
        }
    }

    public static void main(String[] args) throws Exception
    {

        Registry registry = LocateRegistry.getRegistry("localhost", 20202);
        remoteController = (ClientRemoteController) registry.lookup("ClientRemote");

        if( remoteController.registerNode() )
        {
            Thread.sleep(1000);

            DeviceInfo dummyDevice = new DeviceInfo();
            dummyDevice.setHostname("TEST-DEVICE");

            while( true  ) {
                Random rng = new Random();

                dummyDevice.getSystemInfo().setCpu(Math.random());
                dummyDevice.getSystemInfo().setFreeRAM(rng.nextLong());
                dummyDevice.getSystemInfo().setTotalRAM(4096);
                dummyDevice.setUserName("hein");
                dummyDevice.setHostname("host-name");
                dummyDevice.setDomainName("domain-name");

                setProcessList(dummyDevice);
                remoteController.updateNode(dummyDevice);

                Thread.sleep(1000);
            }
        }
    }


}
