package guru.stefma.restapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import guru.stefma.restapi.objects.Token;
import guru.stefma.restapi.objects.Working;
import guru.stefma.restapi.objects.WorkingList;
import guru.stefma.restapi.objects.WorkingMonth;
import guru.stefma.restapi.objects.user.CreateUser;
import guru.stefma.restapi.objects.user.Settings;
import guru.stefma.restapi.objects.user.SettingsWrapper;
import guru.stefma.restapi.objects.user.UserResult;
import guru.stefma.restapi.services.CreateUserService;
import guru.stefma.restapi.services.DeleteWorkingService;
import guru.stefma.restapi.services.GetWorkingMonthService;
import guru.stefma.restapi.services.SaveWorkingService;
import guru.stefma.restapi.services.SettingsService;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    private Retrofit retrofit;

    public ApiHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .client(createHttpClient())
                .build();
    }

    private OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .build();
    }

    private Gson createGson() {
        return new GsonBuilder().serializeNulls().create();
    }

    public void createUser(CreateUser createUser, Callback<UserResult> callback) {
        CreateUserService userService = retrofit.create(CreateUserService.class);
        Call<UserResult> call = userService.create(createUser);
        call.enqueue(callback);
    }

    public void updateSettings(SettingsWrapper settings, Callback<Void> callback) {
        SettingsService settingsService = retrofit.create(SettingsService.class);
        Call<Void> call = settingsService.update(settings);
        call.enqueue(callback);
    }

    public void getSettings(Token token, Callback<Settings> callback) {
        SettingsService settingsService = retrofit.create(SettingsService.class);
        Call<Settings> call = settingsService.get(token);
        call.enqueue(callback);
    }

    public void saveWorking(Working working, Callback<Void> callback) {
        SaveWorkingService workingService = retrofit.create(SaveWorkingService.class);
        Call<Void> call = workingService.save(working);
        call.enqueue(callback);
    }

    public void getWorkingMonth(WorkingMonth workingMonth, Callback<WorkingList> callback) {
        GetWorkingMonthService workingService = retrofit.create(GetWorkingMonthService.class);
        Call<WorkingList> call = workingService.get(workingMonth);
        call.enqueue(callback);
    }

    public void deleteWork(Working working, Callback<Void> callback) {
        DeleteWorkingService deleteWorking = retrofit.create(DeleteWorkingService.class);
        Call<Void> call = deleteWorking.delete(working);
        call.enqueue(callback);
    }

}
