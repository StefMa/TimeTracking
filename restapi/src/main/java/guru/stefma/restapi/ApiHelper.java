package guru.stefma.restapi;

import guru.stefma.restapi.objects.Working;
import guru.stefma.restapi.objects.WorkingList;
import guru.stefma.restapi.objects.WorkingMonth;
import guru.stefma.restapi.services.DeleteWorkingService;
import guru.stefma.restapi.services.GetWorkingMonthService;
import guru.stefma.restapi.services.SaveWorkingService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    private Retrofit retrofit;

    public ApiHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
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
