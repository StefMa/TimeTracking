package guru.stefma.restapi.services;

import guru.stefma.restapi.objects.Working;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeleteWorkingService {

    @Headers("Content-Type: application/json")
    @POST("/rest/delete")
    Call<Void> delete(@Body Working working);

}
