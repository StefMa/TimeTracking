package guru.stefma.timetracking.main;

import android.app.Activity;
import android.content.Intent;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import guru.stefma.restapi.ApiHelper;
import guru.stefma.restapi.objects.WorkList;
import guru.stefma.restapi.objects.Working;
import guru.stefma.restapi.objects.WorkingDay;
import guru.stefma.restapi.objects.WorkingList;
import guru.stefma.restapi.objects.WorkingMonth;
import guru.stefma.timetracking.AddTimeTrackActivity;
import guru.stefma.timetracking.CalendarViewUtils;
import guru.stefma.timetracking.decorator.DecoratorUtils;
import guru.stefma.timetracking.decorator.TimeTrackDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MainPresenter {

    private static final int START_CALENDAR_YEAR = 2016;

    private static final int START_CALENDAR_MONTH = 3;

    private static final int START_CALENDAR_DAY = 1;

    public static final int TIME_TRACK_REQUEST_CODE = 99;

    private final MainView mView;

    private final String mUserToken;

    public MainPresenter(MainView view, String userToken) {
        mView = view;
        mUserToken = userToken;
    }

    public void onViewReady() {
        CalendarDay minimumDay = CalendarDay.from(START_CALENDAR_YEAR, START_CALENDAR_MONTH, START_CALENDAR_DAY);
        CalendarDay currentDay = CalendarDay.from(new Date(System.currentTimeMillis()));
        mView.setupCalendarView(minimumDay, currentDay);
        getWorkingList(currentDay);
    }

    public void onCalendarDateSelected(MaterialCalendarView widget, CalendarDay date) {
        mView.startTimeTrackActivity(date, TIME_TRACK_REQUEST_CODE);
    }

    public void onCalendarMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        getWorkingList(date);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TIME_TRACK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Working working = data.getParcelableExtra(AddTimeTrackActivity.KEY_SAVED_WORKING);
                WorkingDay workingDay = working.getWorkingDay();
                CalendarDay calendarDay = CalendarViewUtils.from(workingDay);
                getWorkingList(calendarDay);
            }
        }
    }

    private void getWorkingList(CalendarDay currentDate) {
        WorkingMonth workingMonth = CalendarViewUtils.from(currentDate, mUserToken);
        new ApiHelper().getWorkingMonth(workingMonth, new Callback<WorkingList>() {
            @Override
            public void onResponse(Call<WorkingList> call, Response<WorkingList> response) {
                if (response.isSuccessful()) {
                    WorkingList workingList = response.body();
                    List<TimeTrackDecorator> decorators = createDecorators(workingList);
                    mView.setDecorators(decorators);
                    float workHourSum = calculatorWorkingHours(workingList);
                    mView.setWorkTimSum(workHourSum);
                } else {
                    mView.showSnackbarError();
                }
            }

            @Override
            public void onFailure(Call<WorkingList> call, Throwable t) {
                t.printStackTrace();
                mView.showSnackbarError();
            }
        });
    }

    private List<TimeTrackDecorator> createDecorators(WorkingList workingList) {
        List<WorkList> workList = workingList.getWorkList();
        List<List<WorkList>> filteredWorkingList = DecoratorUtils.filterWorkListByDay(workList);
        List<TimeTrackDecorator> decorators = new ArrayList<>();
        for (List<WorkList> workListPerDay : filteredWorkingList) {
            TimeTrackDecorator decorator = new TimeTrackDecorator(workListPerDay);
            decorators.add(decorator);
        }
        return decorators;
    }

    private float calculatorWorkingHours(WorkingList workingList) {
        List<WorkList> workList = workingList.getWorkList();
        float workHourSum = 0;
        for (WorkList work : workList) {
            float workHour = work.getWorkTime();
            workHourSum += workHour;
        }
        return workHourSum;
    }

}