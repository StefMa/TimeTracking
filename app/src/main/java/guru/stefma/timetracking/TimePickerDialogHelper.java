package guru.stefma.timetracking;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TimePicker;

public class TimePickerDialogHelper implements TimePickerDialog.OnTimeSetListener {

    private final TimeTrackView mTimeTrackView;

    private final TimeSetListener mTimeSetListener;

    @TimeTrackView.Time
    private final String mTime;

    interface TimeSetListener {
        void onTimeSet(TimeTrackView timeTextView,
                       @TimeTrackView.Time String time,
                       int hourOfDay, int minute);
    }

    public TimePickerDialogHelper(Context context,
                                  TimeTrackView timeTrackView,
                                  @TimeTrackView.Time String time,
                                  @NonNull TimeSetListener timeSetListener) {
        mTimeTrackView = timeTrackView;
        mTime = time;
        mTimeSetListener = timeSetListener;
        new TimePickerDialog(context, this, 0, 0, true).show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        mTimeSetListener.onTimeSet(mTimeTrackView, mTime, hourOfDay, minute);
    }
}
