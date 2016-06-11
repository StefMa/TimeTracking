package guru.stefma.restapi.services;

import guru.stefma.restapi.objects.Token;
import guru.stefma.restapi.objects.user.CreateUser;
import guru.stefma.restapi.objects.user.UserResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginUserService {

    @Headers("Content-Type: application/json")
    @POST("/rest/user/login")
    Call<UserResult> login(@Body Token token);

}
