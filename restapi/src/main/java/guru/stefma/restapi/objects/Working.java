package guru.stefma.restapi.objects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Working {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("working_day")
    @Expose
    private WorkingDay workingDay;
    @SerializedName("double_working")
    @Expose
    private Boolean doubleWorking;
    @SerializedName("first_work")
    @Expose
    private Work firstWork;
    @SerializedName("second_work")
    @Expose
    private Work secondWork;

    /**
     * @return The token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return The workingDay
     */
    public WorkingDay getWorkingDay() {
        return workingDay;
    }

    /**
     * @param workingDay The working_day
     */
    public void setWorkingDay(WorkingDay workingDay) {
        this.workingDay = workingDay;
    }

    /**
     * @return The doubleWorking
     */
    public Boolean getDoubleWorking() {
        return doubleWorking;
    }

    /**
     * @param doubleWorking The double_working
     */
    public void setDoubleWorking(Boolean doubleWorking) {
        this.doubleWorking = doubleWorking;
    }

    /**
     * @return The firstWork
     */
    public Work getFirstWork() {
        return firstWork;
    }

    /**
     * @param firstWork The first_work
     */
    public void setFirstWork(Work firstWork) {
        this.firstWork = firstWork;
    }

    /**
     * @return The secondWork
     */
    public Work getSecondWork() {
        return secondWork;
    }

    /**
     * @param secondWork The second_work
     */
    public void setSecondWork(Work secondWork) {
        this.secondWork = secondWork;
    }

}
