package guru.stefma.timetracking;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

public class AddTimeTrackActivity extends AppCompatActivity
        implements TimePickerDialog.OnTimeSetListener {

    private static final String KEY_DAY = "KEY_DAY";

    private LinearLayout mTimeTrackContainer;

    private ArrayList<TimeTrackView> mTimTracks = new ArrayList<>();

    public static Intent newInstance(Context context, CalendarDay date) {
        Intent intent = new Intent();
        intent.setClass(context, AddTimeTrackActivity.class);
        intent.putExtra(KEY_DAY, date);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtimetrack_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTimeTrackContainer = (LinearLayout) findViewById(R.id.addtimetrack_time_track_container);
        setupFab();

        addNewTimeTrack();

        showTimePickerDialog();
    }

    private void setupFab() {
        //noinspection ConstantConditions
        findViewById(R.id.addtimetrack_add_time_track)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addNewTimeTrack();
                    }
                });
    }

    private void addNewTimeTrack() {
        final TimeTrackView trackView = new TimeTrackView(this);
        ViewGroup.MarginLayoutParams layoutParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, (int) getResources().getDimension(R.dimen.small), 0, 0);
        trackView.setLayoutParams(layoutParams);
        trackView.setOnRemoveClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeTimeTrack(view, trackView);
            }
        });
        mTimeTrackContainer.addView(trackView);
        mTimTracks.add(trackView);
    }

    private void removeTimeTrack(View view, TimeTrackView trackView) {
        if (mTimeTrackContainer.getChildCount() > 1) {
            mTimeTrackContainer.removeView(trackView);
            mTimTracks.remove(trackView);
        } else {
            Snackbar.make(view, getString(R.string.error_remove_track_view),
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
    }
}
