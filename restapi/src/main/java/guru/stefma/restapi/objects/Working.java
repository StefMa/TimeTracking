package guru.stefma.restapi.objects;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Working {

    @SerializedName("token")
    private String mToken;

    @SerializedName("working_day")
    private WorkingDay mWorkingDay;

    @SerializedName("work")
    private List<Work> mWorkList;

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    public WorkingDay getWorkingDay() {
        return mWorkingDay;
    }

    public void setWorkingDay(WorkingDay workingDay) {
        this.mWorkingDay = workingDay;
    }

    public List<Work> getWorkList() {
        return mWorkList;
    }

    public void setWorkList(List<Work> workList) {
        this.mWorkList = workList;
    }

}
