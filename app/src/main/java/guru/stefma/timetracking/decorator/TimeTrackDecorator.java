package guru.stefma.timetracking.decorator;

import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.text.style.ImageSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import guru.stefma.restapi.objects.Work;
import guru.stefma.restapi.objects.WorkingDay;
import guru.stefma.timetracking.CalendarViewUtils;

public class TimeTrackDecorator implements DayViewDecorator {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            NORMAL_WORK,
            ILLNESS,
            VACATION
    })
    private @interface SpanMode {
    }

    private CalendarDay mDay;

    private List<Work> mDaysToDecorate;

    private Drawable mIllnessDrawable;

    private Drawable mVacationDrawable;

    private Object mTimeTrackSpan;

    private static final int NORMAL_WORK = 0;

    private static final int ILLNESS = 1;

    private static final int VACATION = 2;

    public static TimeTrackDecorator create(WorkingDay workingDay) {
        return new TimeTrackDecorator(workingDay);
    }

    TimeTrackDecorator(WorkingDay workingDay) {
        mDay = CalendarViewUtils.from(workingDay);
    }

    public TimeTrackDecorator withWorkList(List<Work> daysToDecorate) {
        mDaysToDecorate = daysToDecorate;
        return this;
    }

    public TimeTrackDecorator illness(Drawable illnessDrawable) {
        mIllnessDrawable = illnessDrawable;
        return this;
    }

    public TimeTrackDecorator vacation(Drawable vacationDrawable) {
        mVacationDrawable = vacationDrawable;
        return this;
    }

    public TimeTrackDecorator build() {
        return new TimeTrackDecorator(mDay, mDaysToDecorate, mIllnessDrawable, mVacationDrawable);
    }

    TimeTrackDecorator(CalendarDay day, List<Work> daysToDecorate,
                       Drawable illness, Drawable vacation) {
        mDay = day;

        @SpanMode
        int spanMode = validateWork(daysToDecorate);
        switch (spanMode) {
            case NORMAL_WORK:
                List<Float> workHours = new ArrayList<>(daysToDecorate.size());
                for (Work work : daysToDecorate) {
                    float workHour = work.getWorkTime();
                    workHours.add(workHour);
                }

                mTimeTrackSpan = new TimeTrackSpan(daysToDecorate.size(), workHours);
                break;
            case ILLNESS:
                illness.setBounds(0, 0, illness.getIntrinsicWidth(),
                        illness.getIntrinsicHeight());
                mTimeTrackSpan = new ImageSpan(illness);
                break;
            case VACATION:
                vacation.setBounds(0, 0, illness.getIntrinsicWidth(),
                        vacation.getIntrinsicHeight());
                mTimeTrackSpan = new ImageSpan(vacation);
                break;
        }

    }

    @SpanMode
    private int validateWork(List<Work> daysToDecorate) {
        if (daysToDecorate.size() > 1) {
            return NORMAL_WORK;
        }
        if (daysToDecorate.size() == 1) {
            Work work = daysToDecorate.get(0);
            if (work.getIllness()) {
                return ILLNESS;
            }
            if (work.getVacation()) {
                return VACATION;
            }
        }
        return NORMAL_WORK;
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
