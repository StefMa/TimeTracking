package guru.stefma.timetracking.settings;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import guru.stefma.timetracking.R;

public class DefaultWorkingHoursDialogView extends FrameLayout {

    private final WorkingHoursView mWorkingHoursView;

    private final ProgressBar mProgressBar;

    public DefaultWorkingHoursDialogView(Context context) {
        super(context);

        inflate(context, R.layout.dialog_default_working_hours, this);
        mWorkingHoursView = (WorkingHoursView)
                findViewById(R.id.default_working_hours_whview);
        mWorkingHoursView.setHoursRange(0, 23);
        mWorkingHoursView.setMinuteRange(0, 59);

        mProgressBar = (ProgressBar) findViewById(R.id.default_working_hours_progress);
    }

    public void setWorkingHours(float workingHours) {
        int hours = (int) workingHours;
        int minutes = Math.round((workingHours % 1) * 60);

        mWorkingHoursView.setHours(hours);
        mWorkingHoursView.setMinutes(minutes);
    }

    public void showLoadingViews() {
        mWorkingHoursView.setVisibility(GONE);
        mProgressBar.setVisibility(VISIBLE);
    }

    public float getWorkingHours() {
        int hours = mWorkingHoursView.getHours();
        float minutes = (float) mWorkingHoursView.getMinutes() / 60;

        return hours + minutes;
    }

}
