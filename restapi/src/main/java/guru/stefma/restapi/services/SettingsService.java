package guru.stefma.restapi.services;

import guru.stefma.restapi.objects.Token;
import guru.stefma.restapi.objects.user.Settings;
import guru.stefma.restapi.objects.user.SettingsWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SettingsService {

    @Headers("Content-Type: application/json")
    @POST("/rest/settings/update")
    Call<Void> update(@Body SettingsWrapper settingsWrapper);

    @Headers("Content-Type: application/json")
    @POST("/rest/settings/get")
    Call<Settings> get(@Body Token token);
}
