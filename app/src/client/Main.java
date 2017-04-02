package client;

import shared.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;


public class Main {
    private static ClientRemoteController remoteController;
    /**
     * Set the a test process list on a device devInfo
     * @param devInfo
     */
    static void setDummyProcessList(DeviceInfo devInfo)
    {
        int count = 3;
        devInfo.getProcessInfo().clear();
        for (int i = 0; i < count; i++) {
            ProcessInfo pinfo = new ProcessInfo();
            pinfo.setPid(i);
            pinfo.setName("process" + i);
            pinfo.setPath("/bin/" + i + "/process" + i);
            pinfo.setUptime(355);
            devInfo.getProcessInfo().add(pinfo);

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
        /*
        Check command line arguments
         */
        if (args.length < 2) {
            System.out.println("Usage: client <ip> <portnumber>");
            System.exit(1);
        }

        // port and ip of the server
        final String ip = args[0];
        final int portNumber = Integer.parseInt(args[1]);

        // Get the remote registry
        Registry registry = LocateRegistry.getRegistry(ip, portNumber);
        remoteController = (ClientRemoteController) registry.lookup("ClientRemote");

        if( remoteController.registerNode() )
        {
            Thread.sleep(1000);

            // Construct a test device info - we don't use actual data
            DeviceInfo dummyDevice = new DeviceInfo();
            dummyDevice.setHostname("TEST-DEVICE");

            while (true) {
                Random rng = new Random();

                // Set test data
                dummyDevice.getSystemInfo().setCpu(Math.random());
                dummyDevice.getSystemInfo().setFreeRAM(rng.nextLong() % 4096);
                dummyDevice.getSystemInfo().setTotalRAM(4096);
                dummyDevice.setUserName("hein");
                dummyDevice.setHostname("host-name");
                dummyDevice.setDomainName("domain-name");

                setDummyProcessList(dummyDevice);

                // Update the node on the remote
                remoteController.updateNode(dummyDevice);

                // Check if we should shutdown
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
