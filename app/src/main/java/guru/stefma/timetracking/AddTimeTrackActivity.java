package guru.stefma.timetracking;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TimePicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class AddTimeTrackActivity extends AppCompatActivity
        implements TimePickerDialog.OnTimeSetListener {

    private static final String KEY_DAY = "KEY_DAY";

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

        showTimePickerDialog();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
    }
}
