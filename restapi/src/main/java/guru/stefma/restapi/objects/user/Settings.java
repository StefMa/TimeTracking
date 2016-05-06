package guru.stefma.restapi.objects.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Settings implements Parcelable {

    public static final Creator<Settings> CREATOR = new Creator<Settings>() {
        @Override
        public Settings createFromParcel(Parcel in) {
            return new Settings(in);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    };

    @SerializedName("default_worktime")
    private float mDefaultWorktime;

    public Settings() {

    }

    protected Settings(Parcel in) {
        mDefaultWorktime = in.readFloat();
    }

    public float getDefaultWorktime() {
        return mDefaultWorktime;
    }

    public void setDefaultWorktime(float defaultWorktime) {
        mDefaultWorktime = defaultWorktime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(mDefaultWorktime);
    }
}
