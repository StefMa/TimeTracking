package guru.stefma.timetracking.settings;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import guru.stefma.restapi.ApiHelper;
import guru.stefma.restapi.objects.Token;
import guru.stefma.restapi.objects.user.Settings;
import guru.stefma.restapi.objects.user.SettingsWrapper;
import guru.stefma.timetracking.BuildConfig;
import guru.stefma.timetracking.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends PreferenceFragmentCompat {

    private Preference mDefaultWorkHoursPref;

    private float mCurrentDefaultWorkHours;

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        getPreferenceManager().setSharedPreferencesName("XX");
        addPreferencesFromResource(R.xml.settings_preferences);

        mDefaultWorkHoursPref = findPreference(getString(R.string.preference_key_default_working_hours));
        mDefaultWorkHoursPref.setEnabled(false);

        new ApiHelper().getSettings(new Token(BuildConfig.USER_TOKEN), new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.isSuccessful()) {
                    mCurrentDefaultWorkHours = response.body().getDefaultWorktime();
                    mDefaultWorkHoursPref.setSummary(String.valueOf(mCurrentDefaultWorkHours));
                    setClickListener();
                }
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
                t.printStackTrace();
                //noinspection ConstantConditions
                Snackbar.make(getView(), R.string.default_error, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setClickListener() {
        mDefaultWorkHoursPref.setEnabled(true);
        mDefaultWorkHoursPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(final Preference preference) {
                final DefaultWorkingHoursDialogView dialogView =
                        new DefaultWorkingHoursDialogView(getContext());
                dialogView.setWorkingHours(mCurrentDefaultWorkHours);
                final AlertDialog dialog = createDialog(dialogView);
                dialog.show();
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogView.showLoadingViews();
                                dialog.setTitle(getString(R.string.save_your_default_time));
                                view.setVisibility(View.GONE);

                                sendDefaultWorkingHours(dialogView, dialog);
                            }
                        });
                return true;
            }
        });
    }

    private AlertDialog createDialog(DefaultWorkingHoursDialogView dialogView) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext(), R.style.Dialog)
                .setTitle(getString(R.string.preference_default_working_hours))
                .setView(dialogView)
                .setPositiveButton(getString(R.string.save), null);
        return dialogBuilder.create();
    }

    private void sendDefaultWorkingHours(final DefaultWorkingHoursDialogView dialogView,
                                         final AlertDialog dialog) {
        Settings settings = new Settings();
        settings.setDefaultWorktime(dialogView.getWorkingHours());
        new ApiHelper().updateSettings(new SettingsWrapper(BuildConfig.USER_TOKEN, settings),
                new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            dialog.cancel();
                            float workingHours = dialogView.getWorkingHours();
                            mDefaultWorkHoursPref.setSummary(String.valueOf(workingHours));
                            mCurrentDefaultWorkHours = workingHours;

                            SettingsManager.saveDefaultWorkingHours(getContext(), workingHours);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        dialog.cancel();
                        //noinspection ConstantConditions
                        Snackbar.make(getView(), R.string.default_error, Snackbar.LENGTH_LONG).show();
                    }
                });
    }

}
