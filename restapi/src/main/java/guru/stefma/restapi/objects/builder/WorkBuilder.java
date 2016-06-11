package guru.stefma.restapi.objects.builder;

import guru.stefma.restapi.objects.Work;

public class WorkBuilder {

    private final WorkingBuilder mWorkingBuilder;

    private TimeBuilder mStartTimeBuilder;

    private TimeBuilder mEndTimeBuilder;

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

    public WorkingBuilder build() {
        return mWorkingBuilder;
    }

    Work buildWork() {
        Work work = new Work();
        work.setStartTime(mStartTimeBuilder.buildTime());
        work.setEndTime(mEndTimeBuilder.buildTime());
        return work;
    }
}
