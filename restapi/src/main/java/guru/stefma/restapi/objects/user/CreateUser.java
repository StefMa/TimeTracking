package guru.stefma.restapi.objects.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CreateUser implements Parcelable {

    public static final Creator<CreateUser> CREATOR = new Creator<CreateUser>() {
        @Override
        public CreateUser createFromParcel(Parcel in) {
            return new CreateUser(in);
        }

        @Override
        public CreateUser[] newArray(int size) {
            return new CreateUser[size];
        }
    };

    @SerializedName("username")
    private String mUsername;

    public CreateUser(String username) {
        mUsername = username;
    }

    protected CreateUser(Parcel in) {
        mUsername = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUsername);
    }
}
