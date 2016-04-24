package guru.stefma.restapi.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    @SerializedName("work_in_day")
    private List<Work> mWorkList;

    public WorkList() {

    }

    protected WorkList(Parcel in) {
        mWorkingDay = in.readParcelable(WorkingDay.class.getClassLoader());
        mWorkList = in.createTypedArrayList(Work.CREATOR);
    }

    public WorkingDay getWorkingDay() {
        return mWorkingDay;
    }

    public void setWorkingDay(WorkingDay workingDay) {
        mWorkingDay = workingDay;
    }

    public List<Work> getWorkList() {
        return mWorkList;
    }

    public void setWorkList(List<Work> workList) {
        mWorkList = workList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mWorkingDay, i);
        parcel.writeTypedList(mWorkList);
    }

}
