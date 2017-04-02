package shared;

import java.io.Serializable;
import java.util.ArrayList;


public class DeviceInfo implements Serializable
{
    private String hostname;
    private String domainName;
    private String userName;
    private ArrayList<ProcessInfo> processInfo;
    private SystemInfo systemInfo;

    public DeviceInfo()
    {
        processInfo = new ArrayList<>();
        systemInfo = new SystemInfo();
    }

    /**
     *
     * @return the list of process info
     */
    public ArrayList<ProcessInfo> getProcessInfo() {
        return processInfo;
    }

    /**
     *
     * @param processInfo list of process info
     */
    public void setProcessInfo(ArrayList<ProcessInfo> processInfo) {
        this.processInfo = processInfo;
    }

    /**
     *
     * @return The device's host name
     */
    public String getHostname() {
        return hostname;
    }

    /**
     *
     * @param hostname The new host name
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     *
     * @return The device's domain name
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     *
     * @param domainName the device's domain name
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     *
     * @return The user name running on the device
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName the device's new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return the device's system inf
     */
    public SystemInfo getSystemInfo() { return systemInfo; }
}
