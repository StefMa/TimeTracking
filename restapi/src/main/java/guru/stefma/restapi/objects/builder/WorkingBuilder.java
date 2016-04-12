package guru.stefma.restapi.objects.builder;


import java.util.ArrayList;
import java.util.List;

import guru.stefma.restapi.objects.Work;
import guru.stefma.restapi.objects.Working;
import guru.stefma.restapi.objects.WorkingDay;

public class WorkingBuilder {

    private String mToken;

    private Integer mDay;

    private Integer mMonth;

    private Integer mYear;

    private List<WorkBuilder> mWorkBuilderList = new ArrayList<>();

    public WorkingBuilder token(String token) {
        this.mToken = token;
        return this;
    }

    public WorkingBuilder day(Integer day) {
        this.mDay = day;
        return this;
    }

    public WorkingBuilder month(Integer month) {
        this.mMonth = month;
        return this;
    }

    public WorkingBuilder year(Integer year) {
        this.mYear = year;
        return this;
    }

    public WorkBuilder work() {
        WorkBuilder workBuilder = new WorkBuilder(this);
        mWorkBuilderList.add(workBuilder);
        return workBuilder;
    }

    public Working build() {
        WorkingDay workingDay = new WorkingDay();
        workingDay.setDay(mDay);
        workingDay.setMonth(mMonth);
        workingDay.setYear(mYear);

        List<Work> workList = new ArrayList<>(mWorkBuilderList.size());
        for (WorkBuilder workBuilder : mWorkBuilderList) {
            workList.add(workBuilder.buildWork());
        }

        Working working = new Working();
        working.setToken(mToken);
        working.setWorkingDay(workingDay);
        working.setWorkList(workList);
        return working;
    }
}
