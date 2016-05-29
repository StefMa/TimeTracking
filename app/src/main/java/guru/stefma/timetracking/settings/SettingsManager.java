package guru.stefma.timetracking.settings;

import android.content.Context;
import android.content.SharedPreferences;

import guru.stefma.timetracking.R;

public class SettingsManager {

    public static final String PREFERENCE_NAME = "pref_file";

    public static void saveDefaultWorkingHours(Context context, float workingHours) {
        SettingsManager.getEditor(context)
                .putFloat(
                        context.getString(R.string.preference_key_default_working_hours),
                        workingHours)
                .apply();
    }

    public static float getDefaultWorkingHours(Context context) {
        return SettingsManager.getSharedPreferences(context).getFloat(
                context.getString(R.string.preference_key_default_working_hours),
                0);
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        return SettingsManager.getSharedPreferences(context).edit();
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }
}
