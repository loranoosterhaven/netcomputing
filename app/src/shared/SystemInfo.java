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

    /**
     * Get the total RAM
     * @return the total RAM of the system
     */
    public long getTotalRAM() {
        return totalRAM;
    }

    /**
     * Set the amount of total
     * @param totalRAM the total amount of RAM on the system
     */
    public void setTotalRAM(long totalRAM) {
        this.totalRAM = totalRAM;
    }

    /**
     * Get the amount of free RAM
     * @return the amount of free RAM on the system
     */
    public long getFreeRAM() {
        return freeRAM;
    }

    /**
     * Get the amount of free RAM
     * @param freeRAM set the amount of free RAM
     */
    public void setFreeRAM(long freeRAM) {
        this.freeRAM = freeRAM;
    }

    /**
     * Get the CPU usage
     * @return the system's CPU usage
     */
    public double getCpu() {
        return cpu;
    }

    /**
     * Set the system's CPU usage
     * @param cpu the new CPU usage
     */
    public void setCpu(double cpu) {
        this.cpu = cpu;
    }
}
