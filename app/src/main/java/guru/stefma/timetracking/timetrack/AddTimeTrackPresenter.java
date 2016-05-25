package guru.stefma.timetracking.timetrack;

import android.content.Intent;

import guru.stefma.restapi.ApiHelper;
import guru.stefma.restapi.objects.Working;
import guru.stefma.timetracking.Presenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTimeTrackPresenter extends Presenter<AddTimeTrackView> {

    public static final String KEY_SAVED_WORKING = "SAVE_WORKING";

    public static final int RESULT_CODE_SAVE_WORK = 2009;

    public static final int RESULT_CODE_DELETE_WORK = 2010;

    public AddTimeTrackPresenter(AddTimeTrackView view) {
        super(view);
    }

    public boolean onSaveActionClick() {
        getView().showSaveActionDialog();
        final Working working = getView().getWorkingToSave();
        new ApiHelper().saveWorking(working, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getView().dismissSaveActionDialog();
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(KEY_SAVED_WORKING, working);
                    getView().finishWithResult(RESULT_CODE_SAVE_WORK, resultIntent);
                } else {
                    getView().dismissSaveActionDialog();
                    getView().showDefaultError();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                getView().dismissSaveActionDialog();
                getView().showDefaultError();
            }
        });
        return true;
    }

    public boolean onDeleteActionClick() {
        getView().showDeleteActionDialog();
        final Working working = getView().getWorkingToDelete();
        new ApiHelper().deleteWork(working, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getView().dismissDeleteActionDialog();
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(KEY_SAVED_WORKING, working);
                    getView().finishWithResult(RESULT_CODE_DELETE_WORK, resultIntent);
                } else {
                    getView().dismissDeleteActionDialog();
                    getView().showDefaultError();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                getView().dismissDeleteActionDialog();
                getView().showDefaultError();
            }
        });
        return true;
    }

    public boolean onIllnessActionClick() {
        getView().showIllnessActionDialog();
        final Working working = getView().getWorkingForIllness();
        new ApiHelper().saveWorking(working, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getView().dismissIllnessActionDialog();
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(KEY_SAVED_WORKING, working);
                    getView().finishWithResult(RESULT_CODE_SAVE_WORK, resultIntent);
                } else {
                    getView().dismissIllnessActionDialog();
                    getView().showDefaultError();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                getView().dismissIllnessActionDialog();
                getView().showDefaultError();
            }
        });
        return true;
    }

    public boolean onVacationActionClick() {
        getView().showVacationActionDialog();
        final Working working = getView().getWorkingForVacation();
        new ApiHelper().saveWorking(working, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getView().dismissVacationActionDialog();
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(KEY_SAVED_WORKING, working);
                    getView().finishWithResult(RESULT_CODE_SAVE_WORK, resultIntent);
                } else {
                    getView().dismissVacationActionDialog();
                    getView().showDefaultError();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                getView().dismissVacationActionDialog();
                getView().showDefaultError();
            }
        });
        return true;
    }
}
