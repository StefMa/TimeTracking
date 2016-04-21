package guru.stefma.restapi.objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class WorkListTest {

    /**
     * WorkTime begins at 6:00 o'clock and end 12:00 o'clock <b>without</b> break
     * Should return 6 hours of work
     *
     * @throws Exception
     */
    @Test
    public void testGetWorkTime_ShouldReturn6Dot0HoursOfWork() throws Exception {
        Time startTime = new Time();
        startTime.setHour(6);
        startTime.setMinute(0);
        Time endTime = new Time();
        endTime.setHour(12);
        endTime.setMinute(0);
        WorkList workList = new WorkList();
        workList.setStartTime(startTime);
        workList.setEndTime(endTime);
        workList.setBreakTime(false);

        float workTime = workList.getWorkTime();

        assertThat(workTime, is(equalTo(6.0f)));
    }

    /**
     * WorkTime begins at 6:30 o'clock and end 12:45 o'clock <b>without</b> break
     * Should return 6.25 hours of work
     *
     * @throws Exception
     */
    @Test
    public void testGetWorkTime_ShouldReturn6Dot25HoursOfWork() throws Exception {
        Time startTime = new Time();
        startTime.setHour(6);
        startTime.setMinute(30);
        Time endTime = new Time();
        endTime.setHour(12);
        endTime.setMinute(45);
        WorkList workList = new WorkList();
        workList.setStartTime(startTime);
        workList.setEndTime(endTime);
        workList.setBreakTime(false);

        float workTime = workList.getWorkTime();

        assertThat(workTime, is(equalTo(6.25f)));
    }

    /**
     * WorkTime begins at 6:15 o'clock and end 12:00 o'clock <b>without</b> break
     * Should return 5.75 hours of work
     *
     * @throws Exception
     */
    @Test
    public void testGetWorkTime_ShouldReturn5Dot75HoursOfWork() throws Exception {
        Time startTime = new Time();
        startTime.setHour(6);
        startTime.setMinute(15);
        Time endTime = new Time();
        endTime.setHour(12);
        endTime.setMinute(0);
        WorkList workList = new WorkList();
        workList.setStartTime(startTime);
        workList.setEndTime(endTime);
        workList.setBreakTime(false);

        float workTime = workList.getWorkTime();

        assertThat(workTime, is(equalTo(5.75f)));
    }

    /**
     * WorkTime begins at 17:15 o'clock and end 20:30 o'clock <b>with</b> break
     * Should return 2.75 hours of work
     *
     * @throws Exception
     */
    @Test
    public void testGetWorkTime_ShouldReturn2Dot75HoursOfWork() throws Exception {
        Time startTime = new Time();
        startTime.setHour(17);
        startTime.setMinute(15);
        Time endTime = new Time();
        endTime.setHour(20);
        endTime.setMinute(30);
        WorkList workList = new WorkList();
        workList.setStartTime(startTime);
        workList.setEndTime(endTime);
        workList.setBreakTime(true);

        float workTime = workList.getWorkTime();

        assertThat(workTime, is(equalTo(2.75f)));
    }

    /**
     * WorkTime begins at 16:30 o'clock and end 23:30 o'clock <b>with</b> break
     * Should return 6.5 hours of work
     *
     * @throws Exception
     */
    @Test
    public void testGetWorkTime_ShouldReturn7Dot0HoursOfWork() throws Exception {
        Time startTime = new Time();
        startTime.setHour(16);
        startTime.setMinute(30);
        Time endTime = new Time();
        endTime.setHour(23);
        endTime.setMinute(30);
        WorkList workList = new WorkList();
        workList.setStartTime(startTime);
        workList.setEndTime(endTime);
        workList.setBreakTime(true);

        float workTime = workList.getWorkTime();

        assertThat(workTime, is(equalTo(6.5f)));
    }

    /**
     * WorkTime begins at 16:02 o'clock and end 17:53 o'clock <b>with</b> break
     * Should return 1.35 hours of work
     *
     * @throws Exception
     */
    @Test
    public void testGetWorkTime_ShouldReturn1Dot85HoursOfWork() throws Exception {
        Time startTime = new Time();
        startTime.setHour(16);
        startTime.setMinute(2);
        Time endTime = new Time();
        endTime.setHour(17);
        endTime.setMinute(53);
        WorkList workList = new WorkList();
        workList.setStartTime(startTime);
        workList.setEndTime(endTime);
        workList.setBreakTime(true);

        float workTime = workList.getWorkTime();

        assertThat(workTime, is(equalTo(1.35f)));
    }
}