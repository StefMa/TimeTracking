package guru.stefma.timetracking.decorator;

import android.support.annotation.NonNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import guru.stefma.restapi.objects.WorkList;
import guru.stefma.restapi.objects.WorkingDay;
import guru.stefma.timetracking.decorator.DecoratorUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class DecoratorUtilsTest {

    @Test
    public void testFilterWorkListByDay_ShouldReturnListFilteredByDay() throws Exception {
        List<WorkList> workList = createTestWorkList();

        List<List<WorkList>> workListByDay = DecoratorUtils.filterWorkListByDay(workList);

        assertThat(workListByDay.size(), is(equalTo(3)));
        List<WorkList> firstWorkList = workListByDay.get(0);
        List<WorkList> secondWorkList = workListByDay.get(1);
        List<WorkList> thirdWorkList = workListByDay.get(2);
        assertThat(firstWorkList.size(), is(equalTo(2)));
        assertThat(firstWorkList.get(0).getWorkingDay().getDay(), is(equalTo(12)));
        assertThat(firstWorkList.get(0).getWorkingDay().getMonth(), is(equalTo(8)));
        assertThat(firstWorkList.get(0).getWorkingDay().getDay(), is(equalTo(12)));
        assertThat(firstWorkList.get(0).getWorkingDay().getMonth(), is(equalTo(8)));
        assertThat(secondWorkList.size(), is(equalTo(1)));
        assertThat(secondWorkList.get(0).getWorkingDay().getDay(), is(equalTo(4)));
        assertThat(secondWorkList.get(0).getWorkingDay().getMonth(), is(equalTo(12)));
        assertThat(thirdWorkList.size(), is(equalTo(2)));
        assertThat(thirdWorkList.get(0).getWorkingDay().getDay(), is(equalTo(5)));
        assertThat(thirdWorkList.get(0).getWorkingDay().getMonth(), is(equalTo(12)));
    }

    @NonNull
    private List<WorkList> createTestWorkList() {
        List<WorkList> workList = new ArrayList<>();

        WorkList list = new WorkList();
        WorkingDay workingDay = new WorkingDay();
        workingDay.setDay(12);
        workingDay.setMonth(8);
        list.setWorkingDay(workingDay);
        workList.add(list);

        list = new WorkList();
        workingDay = new WorkingDay();
        workingDay.setDay(12);
        workingDay.setMonth(8);
        list.setWorkingDay(workingDay);
        workList.add(list);

        list = new WorkList();
        workingDay = new WorkingDay();
        workingDay.setDay(4);
        workingDay.setMonth(12);
        list.setWorkingDay(workingDay);
        workList.add(list);

        list = new WorkList();
        workingDay = new WorkingDay();
        workingDay.setDay(5);
        workingDay.setMonth(12);
        list.setWorkingDay(workingDay);
        workList.add(list);

        list = new WorkList();
        workingDay = new WorkingDay();
        workingDay.setDay(5);
        workingDay.setMonth(12);
        list.setWorkingDay(workingDay);
        workList.add(list);

        return workList;
    }
}