package guru.stefma.restapi.objects;

import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("hour")
    private Integer mHour;

    @SerializedName("minute")
    private Integer mMinute;

    public Integer getHour() {
        return mHour;
    }

    public void setHour(Integer hour) {
        this.mHour = hour;
    }

    public Integer getMinute() {
        return mMinute;
    }

    public void setMinute(Integer minute) {
        this.mMinute = minute;
    }

}