package guru.stefma.restapi.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WorkingMonth implements Parcelable {

    public static final Creator<WorkingMonth> CREATOR = new Creator<WorkingMonth>() {
        @Override
        public WorkingMonth createFromParcel(Parcel in) {
            return new WorkingMonth(in);
        }

        @Override
        public WorkingMonth[] newArray(int size) {
            return new WorkingMonth[size];
        }
    };

    @SerializedName("token")
    private String mToken;

    @SerializedName("year")
    private int mYear;

    @SerializedName("month")
    private int mMonth;

    public WorkingMonth() {

    }

    protected WorkingMonth(Parcel in) {
        mToken = in.readString();
        mYear = in.readInt();
        mMonth = in.readInt();
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    public Integer getYear() {
        return mYear;
    }

    public void setYear(Integer year) {
        this.mYear = year;
    }

    public Integer getMonth() {
        return mMonth;
    }

    public void setMonth(Integer month) {
        this.mMonth = month;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mToken);
        dest.writeInt(mYear);
        dest.writeInt(mMonth);
    }

}
