package guru.stefma.timetracking.timetrack;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.ProgressDialog;
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
import java.util.List;
import java.util.Locale;

import guru.stefma.restapi.ApiHelper;
import guru.stefma.restapi.objects.Work;
import guru.stefma.restapi.objects.WorkList;
import guru.stefma.restapi.objects.Working;
import guru.stefma.timetracking.BuildConfig;
import guru.stefma.timetracking.CalendarViewUtils;
import guru.stefma.timetracking.R;
import guru.stefma.timetracking.SimpleAnimatorListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTimeTrackActivity extends AppCompatActivity
        implements TimePickerDialogHelper.TimeSetListener {

    public static final String KEY_SAVED_WORKING = "SAVE_WORKING";

    public static final int RESULT_CODE_SAVE_WORK = 2009;

    public static final int RESULT_CODE_DELETE_WORK = 2010;

    private static final String KEY_WORK_LIST = "KEY_DAY";

    private static final String TAG = AddTimeTrackActivity.class.getSimpleName();

    private LinearLayout mTimeTrackContainer;

    private MenuItem mSaveAction;

    private WorkList mWorkList;

    public static Intent newInstance(Context context, @NonNull WorkList workList) {
        Intent intent = new Intent();
        intent.setClass(context, AddTimeTrackActivity.class);
        intent.putExtra(KEY_WORK_LIST, workList);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtimetrack_activity);

        Bundle extras = getIntent().getExtras();
        mWorkList = extras.getParcelable(KEY_WORK_LIST);

        setupToolbar();

        mTimeTrackContainer = (LinearLayout) findViewById(R.id.addtimetrack_time_track_container);
        setupFab();

        if (mWorkList.getWorkList() == null) {
            addNewTimeTrack();
        } else {
            addNewTimeTrackFromWork(mWorkList.getWorkList());
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CalendarDay calendarDay = CalendarViewUtils.from(mWorkList.getWorkingDay());
        DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);
        String format = dateInstance.format(calendarDay.getDate());
        //noinspection ConstantConditions
        getSupportActionBar().setTitle(format);
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

    private TimeTrackView addNewTimeTrack() {
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
        return trackView;
    }

    private void addNewTimeTrackFromWork(List<Work> workList) {
        for (Work work : workList) {
            TimeTrackView trackView = addNewTimeTrack();
            trackView.setWork(work);
        }
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
            boolean isValid = trackView.isValid();
            if (!isValid) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addtimetrack, menu);
        mSaveAction = menu.findItem(R.id.addtimetrack_save_action);
        MenuItem deleteAction = menu.findItem(R.id.addtimetrack_delete_action);
        deleteAction.setVisible(mWorkList.getWorkList() != null);
        changeSaveActionState();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        switch (item.getItemId()) {
            case R.id.addtimetrack_save_action:
                progressDialog.setTitle(R.string.save_progress_title);
                progressDialog.setMessage(getString(R.string.save_progress_message));
                progressDialog.show();
                final Working working = WorkingFactory
                        .createWorking(mTimeTrackContainer, mWorkList.getWorkingDay());
                new ApiHelper().saveWorking(working, new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(KEY_SAVED_WORKING, working);
                            setResult(RESULT_CODE_SAVE_WORK, resultIntent);
                            finish();
                        } else {
                            Snackbar.make(mTimeTrackContainer, R.string.default_error,
                                    Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        progressDialog.dismiss();
                        Snackbar.make(mTimeTrackContainer, R.string.default_error,
                                Snackbar.LENGTH_LONG).show();
                    }
                });

                return true;
            case R.id.addtimetrack_delete_action:
                progressDialog.setTitle(R.string.delete_progress_title);
                progressDialog.setMessage(getString(R.string.delete_progress_message));
                progressDialog.show();
                final Working deleteWorking = new Working();
                deleteWorking.setToken(BuildConfig.USER_TOKEN);
                deleteWorking.setWorkingDay(mWorkList.getWorkingDay());
                new ApiHelper().deleteWork(deleteWorking, new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(KEY_SAVED_WORKING, deleteWorking);
                            setResult(RESULT_CODE_DELETE_WORK, resultIntent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Snackbar.make(mTimeTrackContainer, R.string.default_error,
                                    Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        progressDialog.dismiss();
                        Snackbar.make(mTimeTrackContainer, R.string.default_error,
                                Snackbar.LENGTH_LONG).show();
                    }
                });

                return true;
            case R.id.addtimetrack_illness_action:
                progressDialog.setTitle(R.string.illnes_progress_title);
                progressDialog.setMessage(getString(R.string.illness_progress_message));
                progressDialog.show();
                final Working illnessWorking = WorkingFactory
                        .createIllnessWorking(mWorkList.getWorkingDay(), "Illness");
                new ApiHelper().saveWorking(illnessWorking, new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(KEY_SAVED_WORKING, illnessWorking);
                            setResult(RESULT_CODE_DELETE_WORK, resultIntent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Snackbar.make(mTimeTrackContainer, R.string.default_error,
                                    Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        progressDialog.dismiss();
                        Snackbar.make(mTimeTrackContainer, R.string.default_error,
                                Snackbar.LENGTH_LONG).show();
                    }
                });

                return true;
            case R.id.addtimetrack_vacation_action:
                progressDialog.setTitle(R.string.vacation_progress_title);
                progressDialog.setMessage(getString(R.string.vacation_progress_message));
                progressDialog.show();
                final Working vacationWorking = WorkingFactory
                        .createVacationWorking(mWorkList.getWorkingDay(), "Vacation");
                new ApiHelper().saveWorking(vacationWorking, new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(KEY_SAVED_WORKING, vacationWorking);
                            setResult(RESULT_CODE_DELETE_WORK, resultIntent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Snackbar.make(mTimeTrackContainer, R.string.default_error,
                                    Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        progressDialog.dismiss();
                        Snackbar.make(mTimeTrackContainer, R.string.default_error,
                                Snackbar.LENGTH_LONG).show();
                    }
                });

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
