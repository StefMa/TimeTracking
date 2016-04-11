package guru.stefma.restapi.objects;

import com.google.gson.annotations.SerializedName;

public class Work {

    @SerializedName("start_time")
    private Time mStartTime;

    @SerializedName("end_time")
    private Time mEndTime;

    @SerializedName("break_time")
    private Boolean mBreakTime;

    public Time getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Time startTime) {
        this.mStartTime = startTime;
    }

    public Time getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Time endTime) {
        this.mEndTime = endTime;
    }

    public Boolean getBreakTime() {
        return mBreakTime;
    }

    public void setBreakTime(Boolean breakTime) {
        this.mBreakTime = breakTime;
    }

}
