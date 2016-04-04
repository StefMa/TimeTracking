package guru.stefma.restapi.services;

import guru.stefma.restapi.objects.Working;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface SaveWorkingService {
    @Headers("Content-Type: application/json")
    @POST("/rest/save")
    Call<Void> save(@Body Working working);
}
