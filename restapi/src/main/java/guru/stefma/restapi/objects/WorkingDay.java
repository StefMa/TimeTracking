package guru.stefma.restapi.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkingDay extends WorkingMonth {

    @SerializedName("day")
    @Expose
    private Integer day;

    /**
     * @return The day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * @param day The day
     */
    public void setDay(Integer day) {
        this.day = day;
    }
}

