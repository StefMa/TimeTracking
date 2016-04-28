package guru.stefma.restapi.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Work implements Parcelable {

    public static final Creator<Work> CREATOR = new Creator<Work>() {
        @Override
        public Work createFromParcel(Parcel in) {
            return new Work(in);
        }

        @Override
        public Work[] newArray(int size) {
            return new Work[size];
        }
    };

    private static final int MINUTES_PER_HOUR = 60;

    private static final float BREAK_TIME = 0.5f;

    @SerializedName("name")
    private String mName;

    @SerializedName("start_time")
    private Time mStartTime;

    @SerializedName("end_time")
    private Time mEndTime;

    @SerializedName("break_time")
    private boolean mBreakTime;

    public Work() {

    }

    public Work(String name, boolean breakTime, Time startTime, Time endTime) {
        mName = name;
        mBreakTime = breakTime;
        mStartTime = startTime;
        mEndTime = endTime;
    }

    protected Work(Parcel in) {
        mName = in.readString();
        mStartTime = in.readParcelable(Time.class.getClassLoader());
        mEndTime = in.readParcelable(Time.class.getClassLoader());
        mBreakTime = in.readByte() != 0;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeParcelable(mStartTime, i);
        parcel.writeParcelable(mEndTime, i);
        parcel.writeByte((byte) (mBreakTime ? 1 : 0));
    }

    public float getWorkTime() {
        int startTimeHour = mStartTime.getHour();
        int startTimeMinute = mStartTime.getMinute();
        int endTimeHour = mEndTime.getHour();
        int endTimeMinute = mEndTime.getMinute();

        float hour = endTimeHour - startTimeHour;
        float minute = endTimeMinute - startTimeMinute;
        minute = minute / MINUTES_PER_HOUR;

        float workTime = hour + minute;
        if (mBreakTime) {
            workTime -= BREAK_TIME;
        }

        return workTime;
    }
}
