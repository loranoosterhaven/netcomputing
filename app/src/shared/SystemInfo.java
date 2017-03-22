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

    public long getTotalRAM() {
        return totalRAM;
    }

    public void setTotalRAM(long totalRAM) {
        this.totalRAM = totalRAM;
    }

    public long getFreeRAM() {
        return freeRAM;
    }

    public void setFreeRAM(long freeRAM) {
        this.freeRAM = freeRAM;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }
}
