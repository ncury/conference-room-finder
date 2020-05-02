package conferenceroom.beans;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {

    private static final long serialVersionUID = -464923217267951785L;

    private int number;
    private int capacity;
    private List<Hour> availableHours;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Hour> getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(List<Hour> availableHours) {
        this.availableHours = availableHours;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", capacity=" + capacity +
                ", availableHours=" + availableHours +
                '}';
    }
}
