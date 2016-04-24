package guru.stefma.timetracking.decorator;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.List;

import guru.stefma.restapi.objects.WorkList;
import guru.stefma.restapi.objects.WorkingDay;
import guru.stefma.timetracking.CalendarViewUtils;

public class TimeTrackDecorator implements DayViewDecorator {

    private final List<CalendarDay> mDaysToDecorate;

    private final TimeTrackSpan mTimeTrackSpan;

    public TimeTrackDecorator(List<WorkList> daysToDecorate) {
        mDaysToDecorate = new ArrayList<>(daysToDecorate.size());
        for (WorkList work : daysToDecorate) {
            WorkingDay workingDay = work.getWorkingDay();
            CalendarDay calendarDar = CalendarViewUtils.from(workingDay);
            mDaysToDecorate.add(calendarDar);
        }

        List<Float> workHours = new ArrayList<>(daysToDecorate.size());
        for (WorkList workList : daysToDecorate) {
            float workHour = workList.getWorkTime();
            workHours.add(workHour);
        }

        mTimeTrackSpan = new TimeTrackSpan(daysToDecorate.size(), workHours);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return mDaysToDecorate.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(mTimeTrackSpan);
    }

}