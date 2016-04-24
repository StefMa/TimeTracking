package guru.stefma.restapi.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Time implements Parcelable {

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };

    @SerializedName("hour")
    private int mHour;

    @SerializedName("minute")
    private int mMinute;

    public Time() {

    }

    public Time(int hour, int minute) {
        mHour = hour;
        mMinute = minute;
    }

    protected Time(Parcel in) {
        mHour = in.readInt();
        mMinute = in.readInt();
    }

    public int getHour() {
        return mHour;
    }

    public void setHour(int hour) {
        this.mHour = hour;
    }

    public int getMinute() {
        return mMinute;
    }

    public void setMinute(int minute) {
        this.mMinute = minute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mHour);
        parcel.writeInt(mMinute);
    }
}