package com.java.gui;

/**
 * @author Juniors
 */
public class Produce {

    private String name;

    private int PID;

    private int timeSlice;

    private int timeRest;

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
