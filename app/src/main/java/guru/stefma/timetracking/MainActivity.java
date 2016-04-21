package guru.stefma.timetracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;
import java.util.List;

import guru.stefma.restapi.ApiHelper;
import guru.stefma.restapi.objects.WorkList;
import guru.stefma.restapi.objects.Working;
import guru.stefma.restapi.objects.WorkingDay;
import guru.stefma.restapi.objects.WorkingList;
import guru.stefma.restapi.objects.WorkingMonth;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int START_CALENDAR_YEAR = 2016;

    private static final int START_CALENDAR_MONTH = 3;

    private static final int START_CALENDAR_DAY = 1;

    public static final int TIME_TRACK_REQUEST_CODE = 99;

    private MaterialCalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupCalendarView();
    }

    private void setupCalendarView() {
        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendar_view);
        //noinspection ConstantConditions
        mCalendarView.setMinimumDate(CalendarDay.from(START_CALENDAR_YEAR, START_CALENDAR_MONTH, START_CALENDAR_DAY));
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                ActivityCompat.
                        startActivityForResult(
                                MainActivity.this,
                                AddTimeTrackActivity.newInstance(MainActivity.this, date),
                                TIME_TRACK_REQUEST_CODE,
                                null);
            }
        });
        mCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                getWorkingList(date);
            }
        });
        getWorkingList(mCalendarView.getCurrentDate());
    }

    private void getWorkingList(CalendarDay currentDate) {
        WorkingMonth workingMonth = CalendarViewUtils.from(currentDate, getString(R.string.USER_TOKEN));
        new ApiHelper().getWorkingMonth(workingMonth, new Callback<WorkingList>() {
            @Override
            public void onResponse(Call<WorkingList> call, Response<WorkingList> response) {
                if (response.isSuccessful()) {
                    WorkingList workingList = response.body();
                    addDecoratorsToCalendar(workingList);
                } else {
                    Snackbar.make(mCalendarView, R.string.error_calendar_list, Snackbar.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<WorkingList> call, Throwable t) {
                t.printStackTrace();
                Snackbar.make(mCalendarView, R.string.error_calendar_list, Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void addDecoratorsToCalendar(WorkingList workingList) {
        List<WorkList> workList = workingList.getWorkList();
        List<List<WorkList>> filteredWorkingList = DecoratorUtils.filterWorkListByDay(workList);
        ArrayList<TimeTrackDecorator> decorators = new ArrayList<>();
        for (List<WorkList> workListPerDay : filteredWorkingList) {
            TimeTrackDecorator decorator = new TimeTrackDecorator(workListPerDay);
            decorators.add(decorator);
        }
        mCalendarView.addDecorators(decorators);
        mCalendarView.invalidateDecorators();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TIME_TRACK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Working working = data.getParcelableExtra(AddTimeTrackActivity.KEY_SAVED_WORKING);
                WorkingDay workingDay = working.getWorkingDay();
                CalendarDay calendarDay = CalendarViewUtils.from(workingDay);
                getWorkingList(calendarDay);
            }
        }
    }
}
