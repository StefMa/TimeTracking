package guru.stefma.timetracking;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import guru.stefma.restapi.objects.WorkingDay;
import guru.stefma.restapi.objects.WorkingMonth;

/**
 * Month should be increase/decrease.
 * CalendarDay month range (0-11)
 * WorkingDay month range (1-12)
 */
public class CalendarViewUtils {

    public static CalendarDay from(WorkingDay workingDay) {
        return CalendarDay.from(workingDay.getYear(), workingDay.getMonth() - 1, workingDay.getDay());
    }

    public static WorkingDay from(CalendarDay calendarDay) {
        WorkingDay workingDay = new WorkingDay();
        workingDay.setDay(calendarDay.getDay());
        workingDay.setMonth(calendarDay.getMonth() + 1);
        workingDay.setYear(calendarDay.getYear());
        return workingDay;
    }

    public static WorkingMonth from(CalendarDay calendarDay, String token) {
        WorkingMonth workingMonth = new WorkingMonth();
        workingMonth.setToken(token);
        workingMonth.setMonth(calendarDay.getMonth() + 1);
        workingMonth.setYear(calendarDay.getYear());
        return workingMonth;
    }
}
