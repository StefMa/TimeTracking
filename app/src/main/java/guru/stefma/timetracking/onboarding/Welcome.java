package guru.stefma.timetracking.onboarding;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import guru.stefma.timetracking.main.MainActivity;

public class Welcome {

    public static final String KEY_USER_TOKEN = "KEY_USER_TOKEN";

    public static final String KEY_USERNAME = "KEY_USERNAME";

    private final Context mContext;

    private final Bundle mExtras;

    public Welcome(Context context, Bundle extras) {
        mContext = context;
        mExtras = extras;
    }

    public String getUsername() {
        return mExtras.getString(KEY_USERNAME);
    }

    public String getToken() {
        return mExtras.getString(KEY_USER_TOKEN);
    }

    public void onOkClicked(View view) {
        mContext.startActivity(MainActivity.newInstance(mContext));
    }

}
