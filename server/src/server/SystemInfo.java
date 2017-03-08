package server;

import java.io.Serializable;

/**
 * Created by hein on 8-3-17.
 */
public class SystemInfo implements Serializable
{
    private long uptime;
    private long totalRAM;
    private long freeRAM;
}
