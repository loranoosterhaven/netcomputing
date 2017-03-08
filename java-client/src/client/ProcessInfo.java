package client;

import java.util.ArrayList;

/**
 * Created by hein on 8-3-17.
 */

public class ProcessInfo
{
    private int pid;
    private long uptime;
    private ArrayList<ThreadInfo> threadInfo;
    private String name;
    private String path;
}
