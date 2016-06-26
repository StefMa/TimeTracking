package guru.stefma.timetracking;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import io.fabric.sdk.android.Fabric;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Crashlytics crashlytics = new Crashlytics.Builder()
                .core(
                        new CrashlyticsCore.Builder()
                                .disabled(!BuildConfig.CRASHLYTICS_ENABLED)
                                .build())
                .build();
        Fabric.with(this, crashlytics);
    }
}
