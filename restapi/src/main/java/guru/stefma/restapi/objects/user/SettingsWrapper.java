package guru.stefma.restapi.objects.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SettingsWrapper implements Parcelable {

    public static final Creator<SettingsWrapper> CREATOR = new Creator<SettingsWrapper>() {
        @Override
        public SettingsWrapper createFromParcel(Parcel in) {
            return new SettingsWrapper(in);
        }

        @Override
        public SettingsWrapper[] newArray(int size) {
            return new SettingsWrapper[size];
        }
    };

    @SerializedName("token")
    private String mToken;

    @SerializedName("settings")
    private Settings mSettings;

    public SettingsWrapper(String token, Settings settings) {
        mToken = token;
        mSettings = settings;
    }

    protected SettingsWrapper(Parcel in) {
        mToken = in.readString();
        mSettings = in.readParcelable(Settings.class.getClassLoader());
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public Settings getSettings() {
        return mSettings;
    }

    public void setSettings(Settings settings) {
        mSettings = settings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mToken);
        parcel.writeParcelable(mSettings, i);
    }
}
