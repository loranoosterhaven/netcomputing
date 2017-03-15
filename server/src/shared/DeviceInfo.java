package shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hein on 8-3-17.
 */
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

    public ArrayList<ProcessInfo> getProcessInfo() {
        return processInfo;
    }

    public void setProcessInfo(ArrayList<ProcessInfo> processInfo) {
        this.processInfo = processInfo;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SystemInfo getSystemInfo() { return systemInfo; }
}
