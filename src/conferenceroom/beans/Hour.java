package conferenceroom.beans;

import java.io.Serializable;

public class Hour implements Serializable {

    private static final long serialVersionUID = 1437561214539302556L;

    private String startTime;
    private String endTime;

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
}
