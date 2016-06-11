package guru.stefma.restapi.objects.builder;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import guru.stefma.restapi.objects.Work;
import guru.stefma.restapi.objects.Working;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class WorkingBuilderTest extends TestCase {

    @Test
    public void testCreateWorkingBuilder_ShouldSetupWorkingInAPrettyWay() throws Exception {
        Working working = new WorkingBuilder()
                .token("Token")
                .day(1)
                .month(3)
                .year(2016)
                .work()
                .startTime().hour(12).minute(30).build()
                .endTime().hour(16).minute(30).build()
                .build()
                .work()
                .startTime().hour(18).minute(0).build()
                .endTime().hour(20).minute(30).build()
                .build()
                .build();

        assertThat(working.getToken(), is(equalTo("Token")));
        assertThat(working.getWorkingDay().getDay(), is(equalTo(1)));
        assertThat(working.getWorkingDay().getMonth(), is(equalTo(3)));
        assertThat(working.getWorkingDay().getYear(), is(equalTo(2016)));
        Work firstWork = working.getWorkList().get(0);
        assertThat(firstWork.getStartTime().getHour(), is(equalTo(12)));
        assertThat(firstWork.getStartTime().getMinute(), is(equalTo(30)));
        assertThat(firstWork.getEndTime().getHour(), is(equalTo(16)));
        assertThat(firstWork.getEndTime().getMinute(), is(equalTo(30)));
        Work secondWork = working.getWorkList().get(1);
        assertThat(secondWork.getStartTime().getHour(), is(equalTo(18)));
        assertThat(secondWork.getStartTime().getMinute(), is(equalTo(0)));
        assertThat(secondWork.getEndTime().getHour(), is(equalTo(20)));
        assertThat(secondWork.getEndTime().getMinute(), is(equalTo(30)));
    }
}