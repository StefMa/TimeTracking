package guru.stefma.timetracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import guru.stefma.restapi.objects.Working;

public class MainActivity extends AppCompatActivity {

    private static final int START_CALENDAR_YEAR = 2016;

    private static final int START_CALENDAR_MONTH = 3;

    private static final int START_CALENDAR_DAY = 1;

    public static final int TIME_TRACK_REQUEST_CODE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MaterialCalendarView calendarView = (MaterialCalendarView) findViewById(R.id.calendar_view);
        //noinspection ConstantConditions
        calendarView.setMinimumDate(CalendarDay.from(START_CALENDAR_YEAR, START_CALENDAR_MONTH, START_CALENDAR_DAY));
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TIME_TRACK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Working working = data.getParcelableExtra(AddTimeTrackActivity.KEY_SAVED_WORKING);
                // Do what ever you want with working
            }
        }
    }
}
