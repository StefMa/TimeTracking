package guru.stefma.timetracking;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTimeTrackActivity extends AppCompatActivity
        implements TimePickerDialogHelper.TimeSetListener {

    private static final String KEY_DAY = "KEY_DAY";

    private static final String TAG = AddTimeTrackActivity.class.getSimpleName();

    private LinearLayout mTimeTrackContainer;

    private MenuItem mSaveAction;

    public static Intent newInstance(Context context, @NonNull CalendarDay date) {
        Intent intent = new Intent();
        intent.setClass(context, AddTimeTrackActivity.class);
        intent.putExtra(KEY_DAY, date);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtimetrack_activity);

        setupToolbar();

        mTimeTrackContainer = (LinearLayout) findViewById(R.id.addtimetrack_time_track_container);
        setupFab();

        addNewTimeTrack();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        CalendarDay day = extras.getParcelable(KEY_DAY);
        if (day != null) {
            Date date = day.getDate();
            DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);
            String format = dateInstance.format(date);
            //noinspection ConstantConditions
            getSupportActionBar().setTitle(format);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addtimetrack, menu);
        mSaveAction = menu.findItem(R.id.addtimetrack_save_action);
        changeSaveActionState();
        return true;
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
        trackView.setOnStartTimeClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(trackView, TimeTrackView.START_TIME);
            }
        });
        trackView.setOnEndTimeClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(trackView, TimeTrackView.END_TIME);
            }
        });
        mTimeTrackContainer.addView(trackView);
        changeSaveActionState();
    }

    private void removeTimeTrack(View view, final TimeTrackView trackView) {
        if (mTimeTrackContainer.getChildCount() > 1) {
            Animator animator = AnimatorInflater.loadAnimator(this, R.animator.slide_to_right);
            animator.setTarget(trackView);
            animator.addListener(new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animator) {
                    mTimeTrackContainer.removeView(trackView);
                    changeSaveActionState();
                }
            });
            animator.start();
        } else {
            Snackbar.make(view, getString(R.string.error_remove_track_view),
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    private void showTimePickerDialog(TimeTrackView timeTrackView,
                                      @TimeTrackView.Time String time) {
        new TimePickerDialogHelper(this, timeTrackView, time, this);
    }


    @Override
    public void onTimeSet(TimeTrackView timeTextView, @TimeTrackView.Time String time,
                          int hourOfDay, int minute) {
        switch (time) {
            case TimeTrackView.START_TIME:
                timeTextView.setStartTime(hourOfDay, minute);
                break;
            case TimeTrackView.END_TIME:
                timeTextView.setEndTime(hourOfDay, minute);
                break;
        }

        changeSaveActionState();
    }

    private void changeSaveActionState() {
        if (mSaveAction != null) {
            if (checkIfAllTimeTrackViewsReady()) {
                Drawable wrappedDrawable = DrawableCompat.wrap(mSaveAction.getIcon());
                DrawableCompat.setTint(wrappedDrawable, getResources().getColor(android.R.color.white));
                mSaveAction.setEnabled(true);
                mSaveAction.setIcon(wrappedDrawable);
            } else {
                mSaveAction.setEnabled(false);
                Drawable wrappedDrawable = DrawableCompat.wrap(mSaveAction.getIcon());
                DrawableCompat.setTint(wrappedDrawable, getResources().getColor(R.color.gray));
                mSaveAction.setIcon(wrappedDrawable);
            }
        }
    }

    private boolean checkIfAllTimeTrackViewsReady() {
        int childCount = mTimeTrackContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TimeTrackView trackView = (TimeTrackView) mTimeTrackContainer.getChildAt(i);
            boolean isFullFilled = trackView.isFullFilled();
            if (!isFullFilled) {
                return false;
            }
        }
        return true;
    }
}
