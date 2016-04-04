package guru.stefma.restapi.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkingMonth {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("month")
    @Expose
    private Integer month;

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
     * @return The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param year The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return The month
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * @param month The month
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

}
