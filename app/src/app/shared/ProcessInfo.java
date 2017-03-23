package app.shared;

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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public ArrayList<ThreadInfo> getThreadInfo() {
        return threadInfo;
    }

    public void setThreadInfo(ArrayList<ThreadInfo> threadInfo) {
        this.threadInfo = threadInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
