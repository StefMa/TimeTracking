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

    @SerializedName("working_day")
    private WorkingDay mWorkingDay;

    @SerializedName("start_time")
    private Time mStartTime;

    @SerializedName("end_time")
    private Time mEndTime;

    @SerializedName("break_time")
    private boolean mBreakTime;

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
}
