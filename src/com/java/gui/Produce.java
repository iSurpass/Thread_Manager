package com.java.gui;

/**
 * 进程类
 * @author Juniors
 */
public class Produce {

    /**
     * 进程名
     */
    private String name;

    /**
     * 进程PID
     */
    private int PID;

    /**
     * 时间片
     */
    private int timeSlice;

    /**
     * 剩余时间
     */
    private int timeRest;

    /**
     * 进程状态
     */
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getTimeSlice() {
        return timeSlice;
    }

    public void setTimeSlice(int timeSlice) {
        this.timeSlice = timeSlice;
    }

    public int getTimeRest() {
        return timeRest;
    }

    public void setTimeRest(int timeRest) {
        this.timeRest = timeRest;
    }
}
