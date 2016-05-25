package guru.stefma.timetracking.timetrack;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import guru.stefma.restapi.objects.Time;
import guru.stefma.restapi.objects.Work;
import guru.stefma.restapi.objects.Working;
import guru.stefma.restapi.objects.WorkingDay;
import guru.stefma.timetracking.BuildConfig;

public class WorkingFactory {

    public static Working createWorking(ViewGroup timeTrackViewContainer, WorkingDay workingDay) {
        int childCount = timeTrackViewContainer.getChildCount();
        List<Work> workList = new ArrayList<>(childCount);
        for (int i = 0; i < childCount; i++) {
            TimeTrackView trackView = (TimeTrackView) timeTrackViewContainer.getChildAt(i);
            Time startTime = new Time(trackView.getStartTimeHour(), trackView.getStartTimeMinute());
            Time endTime = new Time(trackView.getEndTimeHour(), trackView.getEndTimeMinute());
            Work work = new Work(trackView.getName(), trackView.hasBreak(), startTime, endTime);
            workList.add(work);
        }

        return createWorkingInternal(workingDay, workList);
    }

    public static Working createIllnessWorking(WorkingDay workingDay, String name) {
        List<Work> workList = new ArrayList<>();
        workList.add(Work.withIllness(name));

        return createWorkingInternal(workingDay, workList);
    }

    public static Working createVacationWorking(WorkingDay workingDay, String name) {
        List<Work> workList = new ArrayList<>();
        workList.add(Work.withVacation(name));

        return createWorkingInternal(workingDay, workList);
    }

    @NonNull
    private static Working createWorkingInternal(WorkingDay workingDay, List<Work> workList) {
        Working working = new Working();
        working.setToken(BuildConfig.USER_TOKEN);
        working.setWorkingDay(workingDay);
        working.setWorkList(workList);
        return working;
    }

}
