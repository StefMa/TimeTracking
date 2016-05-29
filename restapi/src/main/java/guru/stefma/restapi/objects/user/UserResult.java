package guru.stefma.restapi.objects.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserResult implements Parcelable {

    public static final Creator<UserResult> CREATOR = new Creator<UserResult>() {
        @Override
        public UserResult createFromParcel(Parcel in) {
            return new UserResult(in);
        }

        @Override
        public UserResult[] newArray(int size) {
            return new UserResult[size];
        }
    };

    @SerializedName("username")
    private String mUsername;

    @SerializedName("result")
    private String mResult;

    @SerializedName("token")
    private String mToken;

    public static final String RESULT_OK = "Ok";

    public static final String RESULT_ALREADY_EXIST = "Already exist";

    protected UserResult(Parcel in) {
        mUsername = in.readString();
        mResult = in.readString();
        mToken = in.readString();
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        this.mResult = result;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUsername);
        parcel.writeString(mResult);
        parcel.writeString(mToken);
    }
}
