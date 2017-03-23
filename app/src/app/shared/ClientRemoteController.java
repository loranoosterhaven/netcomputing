package app.shared; /**
 * Created by hein on 8-3-17.
 */
import java.rmi.*;
import java.rmi.server.ServerNotActiveException;

public interface ClientRemoteController extends Remote
{
    boolean registerNode() throws Exception;
    boolean unregisterNode() throws Exception;

    boolean updateNode( DeviceInfo deviceInfo ) throws Exception;

    boolean shouldShutdown() throws Exception;
}
