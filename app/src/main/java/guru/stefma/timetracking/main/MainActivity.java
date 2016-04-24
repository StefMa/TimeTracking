package guru.stefma.timetracking.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.List;

import guru.stefma.restapi.objects.WorkList;
import guru.stefma.timetracking.AddTimeTrackActivity;
import guru.stefma.timetracking.R;
import guru.stefma.timetracking.decorator.TimeTrackDecorator;

public class MainActivity extends AppCompatActivity implements MainView {

    public MainPresenter mPresenter;

    private MaterialCalendarView mCalendarView;

    private TextView mWorkSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendar_view);
        mWorkSum = (TextView) findViewById(R.id.work_time_sum);

        mPresenter = new MainPresenter(this);
        mPresenter.onViewReady();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setupCalendarView(@NonNull CalendarDay minimumDay, @NonNull CalendarDay currentDay) {
        mCalendarView.setMinimumDate(minimumDay);
        mCalendarView.setCurrentDate(currentDay);
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mPresenter.onCalendarDateSelected(widget, date);
                mCalendarView.clearSelection();
            }
        });
        mCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                mPresenter.onCalendarMonthChanged(widget, date);
            }
        });
    }

    @Override
    public void startTimeTrackActivity(@NonNull WorkList workList, int requestCode) {
        ActivityCompat.startActivityForResult(
                this,
                AddTimeTrackActivity.newInstance(MainActivity.this, workList),
                requestCode,
                null);
    }

    @Override
    public void showSnackbarError() {
        Snackbar.make(mCalendarView, R.string.error_calendar_list, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setWorkTimSum(float workHourSum) {
        mWorkSum.setText(getString(R.string.work_hour_sum, workHourSum));
    }

    @Override
    public void setDecorators(List<TimeTrackDecorator> decorators) {
        mCalendarView.addDecorators(decorators);
        mCalendarView.invalidateDecorators();
    }
}
