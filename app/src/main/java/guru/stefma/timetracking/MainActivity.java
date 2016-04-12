package guru.stefma.timetracking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class MainActivity extends AppCompatActivity {

    private static final int START_CALENDAR_YEAR = 2016;

    private static final int START_CALENDAR_MONTH = 3;

    private static final int START_CALENDAR_DAY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MaterialCalendarView calendarView = (MaterialCalendarView) findViewById(R.id.calendar_view);
        calendarView.setMinimumDate(CalendarDay.from(START_CALENDAR_YEAR, START_CALENDAR_MONTH, START_CALENDAR_DAY));

    }

}
