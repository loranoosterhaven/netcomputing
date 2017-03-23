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
            pinfo.setName("process" + i);
            pinfo.setPath("/bin/" + i + "/process" + i);
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
        /*if (args.length < 2) {
            System.out.println("Usage: java client <ip> <portnumber>");
            System.exit(1);
        }*/

        // port and ip of the server
        final String ip = "192.168.1.8";//args[0];
        final int portNumber = 20202;//Integer.parseInt(args[1]);

        Registry registry = LocateRegistry.getRegistry(ip, portNumber);
        remoteController = (ClientRemoteController) registry.lookup("ClientRemote");

        if( remoteController.registerNode() )
        {
            Thread.sleep(1000);

            DeviceInfo dummyDevice = new DeviceInfo();
            dummyDevice.setHostname("TEST-DEVICE");

            while( true  ) {
                Random rng = new Random();

                dummyDevice.getSystemInfo().setCpu(Math.random());
                dummyDevice.getSystemInfo().setFreeRAM(rng.nextLong() % 4096);
                dummyDevice.getSystemInfo().setTotalRAM(4096);
                dummyDevice.setUserName("hein");
                dummyDevice.setHostname("host-name");
                dummyDevice.setDomainName("domain-name");

                setProcessList(dummyDevice);
                remoteController.updateNode(dummyDevice);

                if( remoteController.shouldShutdown() )
                {
                    remoteController.unregisterNode();
                    System.exit(0); //FAKE SHUTDOWN
                }
                Thread.sleep(1000);
            }
        }
    }


}
