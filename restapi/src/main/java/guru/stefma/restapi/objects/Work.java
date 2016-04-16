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

    @SerializedName("start_time")
    private Time mStartTime;

    @SerializedName("end_time")
    private Time mEndTime;

    @SerializedName("break_time")
    private boolean mBreakTime;

    public Work() {

    }

    public Work(boolean breakTime, Time startTime, Time endTime) {
        mBreakTime = breakTime;
        mStartTime = startTime;
        mEndTime = endTime;
    }

    protected Work(Parcel in) {
        mStartTime = in.readParcelable(Time.class.getClassLoader());
        mEndTime = in.readParcelable(Time.class.getClassLoader());
        mBreakTime = in.readByte() != 0;
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
        parcel.writeParcelable(mStartTime, i);
        parcel.writeParcelable(mEndTime, i);
        parcel.writeByte((byte) (mBreakTime ? 1 : 0));
    }
}
