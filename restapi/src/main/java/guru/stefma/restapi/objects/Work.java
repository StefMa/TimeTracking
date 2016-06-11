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

    @SerializedName("name")
    private String mName;

    @SerializedName("start_time")
    private Time mStartTime;

    @SerializedName("end_time")
    private Time mEndTime;

    @SerializedName("illness")
    private boolean mIllness;

    @SerializedName("vacation")
    private boolean mVacation;

    public Work() {

    }

    public Work(String name, Time startTime, Time endTime) {
        mName = name;
        mStartTime = startTime;
        mEndTime = endTime;
    }

    public static Work withIllness(String name) {
        Work work = new Work();
        work.mName = name;
        work.mStartTime = Time.empty();
        work.mEndTime = Time.empty();
        work.mIllness = true;
        return work;
    }

    public static Work withVacation(String name) {
        Work work = new Work();
        work.mName = name;
        work.mStartTime = Time.empty();
        work.mEndTime = Time.empty();
        work.mVacation = true;
        return work;
    }

    protected Work(Parcel in) {
        mName = in.readString();
        mStartTime = in.readParcelable(Time.class.getClassLoader());
        mEndTime = in.readParcelable(Time.class.getClassLoader());
        mIllness = in.readByte() != 0;
        mVacation = in.readByte() != 0;
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

    public boolean getIllness() {
        return mIllness;
    }

    public void setIllness(boolean illness) {
        mIllness = illness;
    }

    public boolean getVacation() {
        return mVacation;
    }

    public void setVacation(boolean vacation) {
        mVacation = vacation;
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
        parcel.writeByte((byte) (mIllness ? 1 : 0));
        parcel.writeByte((byte) (mVacation ? 1 : 0));
    }

    public float getWorkTime() {
        int startTimeHour = mStartTime.getHour();
        int startTimeMinute = mStartTime.getMinute();
        int endTimeHour = mEndTime.getHour();
        int endTimeMinute = mEndTime.getMinute();

        float hour = endTimeHour - startTimeHour;
        float minute = endTimeMinute - startTimeMinute;
        minute = minute / MINUTES_PER_HOUR;

        return hour + minute;
    }
}
