package guru.stefma.restapi.services;

import guru.stefma.restapi.objects.WorkingMonth;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetWorkingMonthService {
    @Headers("Content-Type: application/json")
    @POST("/rest/get")
    Call<Void> get(@Body WorkingMonth workingMonth);
}
