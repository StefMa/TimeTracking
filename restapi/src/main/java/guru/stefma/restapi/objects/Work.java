package guru.stefma.restapi.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Work {

    @SerializedName("start_time")
    @Expose
    private Time startTime;
    @SerializedName("end_time")
    @Expose
    private Time endTime;
    @SerializedName("break_time")
    @Expose
    private Boolean breakTime;

    /**
     * @return The startTime
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * @param startTime The start_time
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * @return The endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param endTime The end_time
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * @return The breakTime
     */
    public Boolean getBreakTime() {
        return breakTime;
    }

    /**
     * @param breakTime The break_time
     */
    public void setBreakTime(Boolean breakTime) {
        this.breakTime = breakTime;
    }

}
