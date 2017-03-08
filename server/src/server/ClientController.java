package server; /**
 * Created by hein on 8-3-17.
 */
import java.rmi.*;

public interface ClientController extends Remote
{
    boolean sendObject( DeviceInfo deviceInfo ) throws RemoteException;
}
