package guru.stefma.restapi.objects;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Working implements Parcelable {

    public static final Creator<Working> CREATOR = new Creator<Working>() {
        @Override
        public Working createFromParcel(Parcel in) {
            return new Working(in);
        }

        @Override
        public Working[] newArray(int size) {
            return new Working[size];
        }
    };

    @SerializedName("token")
    private String mToken;

    @SerializedName("working_day")
    private WorkingDay mWorkingDay;

    @SerializedName("work")
    private List<Work> mWorkList;

    public Working() {

    }

    protected Working(Parcel in) {
        mToken = in.readString();
        mWorkList = in.createTypedArrayList(Work.CREATOR);
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    public WorkingDay getWorkingDay() {
        return mWorkingDay;
    }

    public void setWorkingDay(WorkingDay workingDay) {
        this.mWorkingDay = workingDay;
    }

    public List<Work> getWorkList() {
        return mWorkList;
    }

    public void setWorkList(List<Work> workList) {
        this.mWorkList = workList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mToken);
        parcel.writeTypedList(mWorkList);
    }
}
