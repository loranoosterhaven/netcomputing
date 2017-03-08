package server;

import java.io.Serializable;

/**
 * Created by hein on 8-3-17.
 */
public class DeviceInfo implements Serializable
{
    private String hostname;
    private String domainName;
    private String userName;

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
}
