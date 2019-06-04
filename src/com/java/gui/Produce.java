package com.java.gui;

/**
 * @author Juniors
 */
public class Produce {

    private String name;

    private int PID;

    private int timeSlice;

    private String timeRest;

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

    public String getTimeRest() {
        return timeRest;
    }

    public void setTimeRest(String timeRest) {
        this.timeRest = timeRest;
    }
}
