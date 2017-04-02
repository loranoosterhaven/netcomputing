package server;

import shared.DeviceInfo;

public class NodeInfo {
    private String ip;
    private long lastTick;
    private DeviceInfo deviceInfo;

    /**
     * Get the node's ip
     * @return the node's ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Set the node's ip
     * @param ip the new ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Get the last tick of the node
     * @return the last tick of the node
     */
    public long getLastTick() {
        return lastTick;
    }

    /**
     * Set the node's last tick
     * @param lastTick the new lastTick value
     */
    public void setLastTick(long lastTick) {
        this.lastTick = lastTick;
    }

    /**
     * Get the node's device info
     * @return the node's device information
     */
    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    /**
     * Set the node's device info
     * @param deviceInfo the node's device info
     */
    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @Override
    public boolean equals(Object obj)
    {
        if( this == obj )
            return true;

        if( obj == null || (getClass() != obj.getClass()))
            return false;

        NodeInfo other = ( NodeInfo )obj;
        return ip.equals(other.ip);
    }

    @Override
    public int hashCode()
    {
        return ip.hashCode();
    }
}
