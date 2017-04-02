package shared;

import java.io.Serializable;

public class ThreadInfo implements Serializable
{
    private int id;
    private long uptime;

    /**
     * Get the thread ID
     * @return the thread's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set the thread ID
     * @param id the new thread ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the total thread's uptime
     * @return the thread's total uptime
     */
    public long getUptime() {
        return uptime;
    }

    /**
     * Set the thread's total uptime
     * @param uptime the new uptime
     */
    public void setUptime(long uptime) {
        this.uptime = uptime;
    }
}
