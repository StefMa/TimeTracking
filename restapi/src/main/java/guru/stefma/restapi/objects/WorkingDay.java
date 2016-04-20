package guru.stefma.restapi.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WorkingDay extends WorkingMonth implements Parcelable {

    public static final Creator<WorkingDay> CREATOR = new Creator<WorkingDay>() {
        @Override
        public WorkingDay createFromParcel(Parcel in) {
            return new WorkingDay(in);
        }

        @Override
        public WorkingDay[] newArray(int size) {
            return new WorkingDay[size];
        }
    };

    @SerializedName("day")
    private int mDay;

    public WorkingDay() {

    }

    protected WorkingDay(Parcel in) {
        super(in);
        mDay = in.readInt();
    }

    public Integer getDay() {
        return mDay;
    }

    public void setDay(Integer day) {
        this.mDay = day;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(mDay);
    }

    public boolean equalsByDay(WorkingDay workingDay) {
        if (workingDay.getDay().equals(getDay())
                && workingDay.getMonth().equals(getMonth())) {
            return true;
        }
        return false;
    }
}

