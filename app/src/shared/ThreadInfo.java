package shared;

import java.io.Serializable;

/**
 * Created by hein on 8-3-17.
 */
public class ThreadInfo implements Serializable
{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    private long uptime;
}
