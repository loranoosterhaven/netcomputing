package shared;

import java.io.Serializable;

/**
 * Created by hein on 8-3-17.
 */
public class SystemInfo implements Serializable
{
    private long uptime;
    private long totalRAM;
    private long freeRAM;
    private double cpu;

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }
}
