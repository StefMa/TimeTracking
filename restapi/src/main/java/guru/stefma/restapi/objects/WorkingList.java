package guru.stefma.restapi.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkingList implements Parcelable {

    public static final Creator<WorkingList> CREATOR = new Creator<WorkingList>() {
        @Override
        public WorkingList createFromParcel(Parcel in) {
            return new WorkingList(in);
        }

        @Override
        public WorkingList[] newArray(int size) {
            return new WorkingList[size];
        }
    };

    @SerializedName("working_month")
    private WorkingMonth mWorkingMonth;

    @SerializedName("work")
    private List<WorkList> mWorkList;

    protected WorkingList(Parcel in) {
        mWorkingMonth = in.readParcelable(WorkingMonth.class.getClassLoader());
        mWorkList = in.createTypedArrayList(WorkList.CREATOR);
    }

    public WorkingMonth getWorkingMonth() {
        return mWorkingMonth;
    }

    public void setWorkingMonth(WorkingMonth workingMonth) {
        mWorkingMonth = workingMonth;
    }

    public List<WorkList> getWorkList() {
        return mWorkList;
    }

    public void setWorkList(List<WorkList> workList) {
        mWorkList = workList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mWorkingMonth, i);
        parcel.writeTypedList(mWorkList);
    }

    public WorkList getWorkListFromDay(WorkingDay workingDay) {
        List<WorkList> workList = getWorkList();
        for (WorkList workL : workList) {
            WorkingDay day = workL.getWorkingDay();
            if (day.equalsByDay(workingDay)) {
                return workL;
            }
        }
        WorkList emptyList = new WorkList();
        emptyList.setWorkingDay(workingDay);
        return emptyList;
    }
}
