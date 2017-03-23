package app.server;

import app.shared.DeviceInfo;

/**
 * Created by hein on 8-3-17.
 */
public class NodeInfo {
    private String ip;
    private long lastTick;
    private DeviceInfo deviceInfo;

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getLastTick() {
        return lastTick;
    }
    public void setLastTick(long lastTick) {
        this.lastTick = lastTick;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }
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
