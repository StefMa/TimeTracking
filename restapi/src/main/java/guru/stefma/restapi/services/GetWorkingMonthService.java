package guru.stefma.restapi.services;

import guru.stefma.restapi.objects.WorkingMonth;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface GetWorkingMonthService {
    @Headers("Content-Type: application/json")
    @POST("/rest/get")
    Call<Void> get(@Body WorkingMonth workingMonth);
}
