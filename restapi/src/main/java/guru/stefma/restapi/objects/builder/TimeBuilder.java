package guru.stefma.restapi.objects.builder;

import guru.stefma.restapi.objects.Time;

public class TimeBuilder {

    private final WorkBuilder mWorkBuilder;

    private Integer mHour;

    private Integer mMinute;

    public TimeBuilder(WorkBuilder workBuilder) {
        this.mWorkBuilder = workBuilder;
    }

    public TimeBuilder hour(Integer hour) {
        this.mHour = hour;
        return this;
    }

    public TimeBuilder minute(Integer minute) {
        this.mMinute = minute;
        return this;
    }

    public WorkBuilder build() {
        return this.mWorkBuilder;
    }

    Time buildTime() {
        Time time = new Time();
        time.setHour(mHour);
        time.setMinute(mMinute);
        return time;
    }

}
