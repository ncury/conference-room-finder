package conferenceroom.beans;

import java.io.Serializable;

public class Input implements Serializable {

    private static final long serialVersionUID = 8691890765094154610L;

    private int capacity;
    private int floor;
    private String startTime;
    private String endTime;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Input{" +
                "capacity=" + capacity +
                ", floor=" + floor +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
