package guru.stefma.timetracking;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import guru.stefma.restapi.ApiHelper;
import guru.stefma.restapi.objects.Time;
import guru.stefma.restapi.objects.Work;
import guru.stefma.restapi.objects.Working;
import guru.stefma.restapi.objects.WorkingDay;
import guru.stefma.restapi.objects.WorkingMonth;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WorkingDay workingDay = new WorkingDay();
                workingDay.setDay(27);
                workingDay.setYear(2016);
                workingDay.setMonth(2);

                Time startTimeFirstWork = new Time();
                startTimeFirstWork.setHour(7);
                startTimeFirstWork.setMinute(30);
                Time endTimeFirstWork = new Time();
                endTimeFirstWork.setHour(10);
                endTimeFirstWork.setMinute(30);
                Work firstWork = new Work();
                firstWork.setBreakTime(true);
                firstWork.setStartTime(startTimeFirstWork);
                firstWork.setEndTime(endTimeFirstWork);

                Time startTimeSecondWork = new Time();
                startTimeSecondWork.setHour(15);
                startTimeSecondWork.setMinute(30);
                Time endTimeSecondWork = new Time();
                endTimeSecondWork.setHour(19);
                endTimeSecondWork.setMinute(30);
                Work secondWork = new Work();
                secondWork.setBreakTime(false);
                secondWork.setStartTime(startTimeSecondWork);
                secondWork.setEndTime(endTimeSecondWork);

                Working working = new Working();
                working.setToken(getString(R.string.USER_TOKEN));
                working.setDoubleWorking(true);
                working.setWorkingDay(workingDay);
                working.setFirstWork(firstWork);
                working.setSecondWork(secondWork);

                new ApiHelper().saveWorking(working, new Callback<Void>() {
                    @Override
                    public void onResponse(Response<Void> response, Retrofit retrofit) {
                        Log.e("response_code", String.valueOf(response.code()));
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.fillInStackTrace();
                    }
                });
            }
        });

        findViewById(R.id.fab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WorkingMonth workingMonth = new WorkingMonth();
                workingMonth.setMonth(2);
                workingMonth.setToken(getString(R.string.USER_TOKEN));
                workingMonth.setYear(2016);

                new ApiHelper().getWorkingMonth(workingMonth, new Callback<Void>() {
                    @Override
                    public void onResponse(Response<Void> response, Retrofit retrofit) {
                        Log.e("response_code", String.valueOf(response.code()));
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.fillInStackTrace();
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
