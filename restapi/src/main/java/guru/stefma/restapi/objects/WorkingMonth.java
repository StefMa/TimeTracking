package guru.stefma.restapi.objects;

import com.google.gson.annotations.SerializedName;

public class WorkingMonth {

    @SerializedName("token")
    private String mToken;

    @SerializedName("year")
    private Integer mYear;

    @SerializedName("month")
    private Integer mMonth;

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    public Integer getYear() {
        return mYear;
    }

    public void setYear(Integer year) {
        this.mYear = year;
    }

    public Integer getMonth() {
        return mMonth;
    }

    public void setMonth(Integer month) {
        this.mMonth = month;
    }

}
