package guru.stefma.restapi.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WorkList implements Parcelable {

    public static final Creator<WorkList> CREATOR = new Creator<WorkList>() {
        @Override
        public WorkList createFromParcel(Parcel in) {
            return new WorkList(in);
        }

        @Override
        public WorkList[] newArray(int size) {
            return new WorkList[size];
        }
    };

    private static final int MINUTES_PER_HOUR = 60;

    private static final float BREAK_TIME = 0.5f;

    @SerializedName("working_day")
    private WorkingDay mWorkingDay;

    @SerializedName("start_time")
    private Time mStartTime;

    @SerializedName("end_time")
    private Time mEndTime;

    @SerializedName("break_time")
    private boolean mBreakTime;

    public WorkList() {

    }

    protected WorkList(Parcel in) {
        mWorkingDay = in.readParcelable(WorkingDay.class.getClassLoader());
        mStartTime = in.readParcelable(Time.class.getClassLoader());
        mEndTime = in.readParcelable(Time.class.getClassLoader());
        mBreakTime = in.readByte() != 0;
    }

    public WorkingDay getWorkingDay() {
        return mWorkingDay;
    }

    public void setWorkingDay(WorkingDay workingDay) {
        mWorkingDay = workingDay;
    }

    public Time getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Time startTime) {
        mStartTime = startTime;
    }

    public Time getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Time endTime) {
        mEndTime = endTime;
    }

    public boolean isBreakTime() {
        return mBreakTime;
    }

    public void setBreakTime(boolean breakTime) {
        mBreakTime = breakTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mWorkingDay, i);
        parcel.writeParcelable(mStartTime, i);
        parcel.writeParcelable(mEndTime, i);
        parcel.writeByte((byte) (mBreakTime ? 1 : 0));
    }

    public float getWorkTime() {
        Time startTime = getStartTime();
        Time endTime = getEndTime();
        boolean breakTime = isBreakTime();

        Integer startTimeHour = startTime.getHour();
        Integer startTimeMinute = startTime.getMinute();
        Integer endTimeHour = endTime.getHour();
        Integer endTimeMinute = endTime.getMinute();

        float hour = endTimeHour - startTimeHour;
        float minute = endTimeMinute - startTimeMinute;
        minute = minute / MINUTES_PER_HOUR;

        float workTime = hour + minute;
        if (breakTime) {
            workTime -= BREAK_TIME;
        }

        return workTime;
    }
}
