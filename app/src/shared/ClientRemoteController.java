package shared; /**
 * Created by hein on 8-3-17.
 */
import java.rmi.*;
import java.rmi.server.ServerNotActiveException;

public interface ClientRemoteController extends Remote
{
    /**
     * Register a new node
     * @return true if register succesfull, else false
     * @throws Exception
     */
    boolean registerNode() throws Exception;

    /**
     * Unregister a node, given that it is contained in the list
     * @return true if unregister succesfull, else false
     * @throws Exception
     */
    boolean unregisterNode() throws Exception;

    /**
     * Update a client, given that it is contained in the node list
     * @param deviceInfo the new device info
     * @return true if update succesfull, false else
     * @throws Exception
     */
    boolean updateNode( DeviceInfo deviceInfo ) throws Exception;

    /**
     * Checks if a client should shutdown
     * @return true if client should shutdown, false else
     * @throws Exception
     */
    boolean shouldShutdown() throws Exception;
}
