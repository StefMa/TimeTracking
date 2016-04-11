package guru.stefma.restapi.objects;

import com.google.gson.annotations.SerializedName;

public class WorkingDay extends WorkingMonth {

    @SerializedName("day")
    private Integer mDay;

    public Integer getDay() {
        return mDay;
    }

    public void setDay(Integer day) {
        this.mDay = day;
    }
}

