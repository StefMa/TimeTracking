package guru.stefma.restapi.objects.builder;

import guru.stefma.restapi.objects.Work;

public class WorkBuilder {

    private final WorkingBuilder mWorkingBuilder;

    private TimeBuilder mStartTimeBuilder;

    private TimeBuilder mEndTimeBuilder;

    private Boolean mBreakTime;

    public WorkBuilder(WorkingBuilder workingBuilder) {
        this.mWorkingBuilder = workingBuilder;
    }

    public TimeBuilder startTime() {
        mStartTimeBuilder = new TimeBuilder(this);
        return mStartTimeBuilder;
    }

    public TimeBuilder endTime() {
        mEndTimeBuilder = new TimeBuilder(this);
        return mEndTimeBuilder;
    }

    public WorkBuilder breakTime(Boolean breakTime) {
        this.mBreakTime = breakTime;
        return this;
    }

    public WorkingBuilder build() {
        return mWorkingBuilder;
    }

    Work buildWork() {
        Work work = new Work();
        work.setBreakTime(mBreakTime);
        work.setStartTime(mStartTimeBuilder.buildTime());
        work.setEndTime(mEndTimeBuilder.buildTime());
        return work;
    }
}
