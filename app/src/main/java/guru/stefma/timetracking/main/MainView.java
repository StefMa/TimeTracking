package guru.stefma.timetracking.main;

import android.support.annotation.NonNull;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.List;

import guru.stefma.timetracking.decorator.TimeTrackDecorator;

interface MainView {

    void setupCalendarView(@NonNull CalendarDay minimumDay, @NonNull CalendarDay currentDay);

    void startTimeTrackActivity(@NonNull CalendarDay date, int requestCode);

    void showSnackbarError();

    void setWorkTimSum(float workHourSum);

    void setDecorators(List<TimeTrackDecorator> decorators);

}
