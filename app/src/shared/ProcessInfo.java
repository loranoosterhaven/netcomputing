package shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hein on 8-3-17.
 */

public class ProcessInfo implements Serializable
{
    private int pid;
    private long uptime;
    private ArrayList<ThreadInfo> threadInfo = new ArrayList<>();
    private String name;
    private String path;

    /**
     * Get the process id
     * @return the process id
     */
    public int getPid() {
        return pid;
    }

    /**
     *  Set the process id
     * @param pid the pid to be set
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * Get the current uptime
     * @return the current uptime
     */
    public long getUptime() {
        return uptime;
    }

    /**
     * Set the current uptime
     * @param uptime the new uptime
     */
    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    /**
     * Get the list of thread information
     * @return the list of thread information
     */
    public ArrayList<ThreadInfo> getThreadInfo() {
        return threadInfo;
    }

    /**
     * Set the current thread information
     * @param threadInfo new thread information
     */
    public void setThreadInfo(ArrayList<ThreadInfo> threadInfo) {
        this.threadInfo = threadInfo;
    }

    /**
     * Get the process name
     * @return the process name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the process name
     * @param name the new process name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the process path
     * @return the process path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the process path
     * @param path the new process path
     */
    public void setPath(String path) {
        this.path = path;
    }


}
