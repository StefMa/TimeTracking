package guru.stefma.restapi.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("hour")
    @Expose
    private Integer hour;
    @SerializedName("minute")
    @Expose
    private Integer minute;

    /**
     * @return The hour
     */
    public Integer getHour() {
        return hour;
    }

    /**
     * @param hour The hour
     */
    public void setHour(Integer hour) {
        this.hour = hour;
    }

    /**
     * @return The minute
     */
    public Integer getMinute() {
        return minute;
    }

    /**
     * @param minute The minute
     */
    public void setMinute(Integer minute) {
        this.minute = minute;
    }

}