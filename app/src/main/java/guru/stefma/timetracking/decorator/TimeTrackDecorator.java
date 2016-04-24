package guru.stefma.timetracking.decorator;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.List;

import guru.stefma.restapi.objects.Work;
import guru.stefma.restapi.objects.WorkingDay;
import guru.stefma.timetracking.CalendarViewUtils;

public class TimeTrackDecorator implements DayViewDecorator {

    private final CalendarDay mDay;

    private final TimeTrackSpan mTimeTrackSpan;

    public TimeTrackDecorator(WorkingDay workingDay, List<Work> daysToDecorate) {
        mDay = CalendarViewUtils.from(workingDay);

        List<Float> workHours = new ArrayList<>(daysToDecorate.size());
        for (Work workList : daysToDecorate) {
            float workHour = workList.getWorkTime();
            workHours.add(workHour);
        }

        mTimeTrackSpan = new TimeTrackSpan(daysToDecorate.size(), workHours);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return mDay.equals(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(mTimeTrackSpan);
    }

}
