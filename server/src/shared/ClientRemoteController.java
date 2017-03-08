package shared; /**
 * Created by hein on 8-3-17.
 */
import java.rmi.*;
import java.rmi.server.ServerNotActiveException;

public interface ClientRemoteController extends Remote
{
    boolean registerNode() throws RemoteException, ServerNotActiveException;
    boolean unregisterNode() throws RemoteException, ServerNotActiveException;

    boolean updateNode( DeviceInfo deviceInfo ) throws RemoteException, ServerNotActiveException;
}
