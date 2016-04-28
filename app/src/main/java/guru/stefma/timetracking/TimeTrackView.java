package guru.stefma.timetracking;

import android.content.Context;
import android.support.annotation.StringDef;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import guru.stefma.restapi.objects.Work;

public class TimeTrackView extends CardView {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            START_TIME,
            END_TIME,
    })
    public @interface Time {
    }

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    private View mTimeTrackRemove;

    private TextView mTimeTrackName;

    private TextView mTimeTrackStartTime;

    private TextView mTimeTrackEndTime;

    private CheckBox mTimeTrackBreak;

    private int mHourOfDayStartTime;

    private int mMinuteStartTime;

    private int mHourOfDayEndTime;

    private int mMinuteEndTime;

    public TimeTrackView(Context context) {
        this(context, null);
    }

    public TimeTrackView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setCardElevation(getResources().getDimension(R.dimen.cardview_default_elevation));
        setRadius(getResources().getDimension(R.dimen.cardview_default_radius));

        inflate(context, R.layout.view_time_track, this);

        mTimeTrackRemove = findViewById(R.id.time_track_remove);
        mTimeTrackName = (TextView) findViewById(R.id.time_track_name);
        mTimeTrackStartTime = (TextView) findViewById(R.id.time_track_start_time);
        mTimeTrackEndTime = (TextView) findViewById(R.id.time_track_end_time);
        mTimeTrackBreak = (CheckBox) findViewById(R.id.time_track_break);
    }

    public void setOnRemoveClickListener(View.OnClickListener clickListener) {
        setClickListener(mTimeTrackRemove, clickListener);
    }

    public void setOnStartTimeClickListener(View.OnClickListener clickListener) {
        setClickListener(mTimeTrackStartTime, clickListener);
    }

    public void setOnEndTimeClickListener(View.OnClickListener clickListener) {
        setClickListener(mTimeTrackEndTime, clickListener);
    }

    private void setClickListener(View view, View.OnClickListener clickListener) {
        view.setOnClickListener(clickListener);
    }

    public void setWork(Work work) {
        mTimeTrackName.setText(work.getName());
        setStartTime(work.getStartTime().getHour(), work.getStartTime().getMinute());
        setEndTime(work.getEndTime().getHour(), work.getEndTime().getMinute());
        mTimeTrackBreak.setChecked(work.getBreakTime());
    }

    public void setStartTime(int hourOfDay, int minute) {
        mHourOfDayStartTime = hourOfDay;
        mMinuteStartTime = minute;
        String startTime = getContext().getString(R.string.set_start_time,
                getFormattedTime(hourOfDay), getFormattedTime(minute));
        mTimeTrackStartTime.setText(startTime);
    }

    public void setEndTime(int hourOfDay, int minute) {
        mHourOfDayEndTime = hourOfDay;
        mMinuteEndTime = minute;
        String endTime = getContext().getString(R.string.set_end_time,
                getFormattedTime(hourOfDay), getFormattedTime(minute));
        mTimeTrackEndTime.setText(endTime);
    }

    private String getFormattedTime(int time) {
        String timeString = String.valueOf(time);
        if (time < 10) {
            timeString = "0" + timeString;
        }
        return timeString;
    }

    public boolean isValid() {
        if (TextUtils.isEmpty(mTimeTrackStartTime.getText())) {
            return false;
        }
        if (TextUtils.isEmpty(mTimeTrackEndTime.getText())) {
            return false;
        }
        if (!(isEndHourBiggerThanStartHour())
                && !(isEndHourEqualsStartHourAndEndMinuteBiggerThanStartMinute())) {
            return false;
        }

        return true;
    }

    private boolean isEndHourBiggerThanStartHour() {
        return getEndTimeHour() > getStartTimeHour();
    }

    private boolean isEndHourEqualsStartHourAndEndMinuteBiggerThanStartMinute() {
        return getEndTimeHour() == getStartTimeHour()
                && getEndTimeMinute() > getStartTimeMinute();
    }

    public boolean hasBreak() {
        return mTimeTrackBreak.isChecked();
    }

    public String getName() {
        return mTimeTrackName.getText().toString();
    }

    public int getStartTimeHour() {
        return mHourOfDayStartTime;
    }

    public int getStartTimeMinute() {
        return mMinuteStartTime;
    }

    public int getEndTimeHour() {
        return mHourOfDayEndTime;
    }

    public int getEndTimeMinute() {
        return mMinuteEndTime;
    }

}
