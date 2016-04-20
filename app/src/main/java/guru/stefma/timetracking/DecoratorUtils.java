package guru.stefma.timetracking;

import java.util.ArrayList;
import java.util.List;

import guru.stefma.restapi.objects.WorkList;
import guru.stefma.restapi.objects.WorkingDay;

public class DecoratorUtils {

    public static List<List<WorkList>> filterWorkListByDay(List<WorkList> listToFilter) {
        List<List<WorkList>> filteredList = new NoDuplicatedWorkList();
        for (int i = 0; i < listToFilter.size(); i++) {
            List<WorkList> list = new ArrayList<>();
            WorkingDay workingDay = listToFilter.get(i).getWorkingDay();
            for (int x = 0; x < listToFilter.size(); x++) {
                WorkingDay possibleDuplicate = listToFilter.get(x).getWorkingDay();
                if (workingDay.equalsByDay(possibleDuplicate)) {
                    list.add(listToFilter.get(x));
                }
            }
            filteredList.add(list);
        }
        return filteredList;
    }

    private static class NoDuplicatedWorkList extends ArrayList<List<WorkList>> {

        @Override
        public boolean add(List<WorkList> object) {
            if (this.contains(object)) {
                return false;
            }
            return super.add(object);
        }

    }
}
