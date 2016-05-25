package guru.stefma.timetracking.timetrack;

import android.content.Intent;

import guru.stefma.restapi.objects.Working;

public interface AddTimeTrackView {

    void showSaveActionDialog();

    void dismissSaveActionDialog();

    Working getWorkingToSave();

    void showDefaultError();

    void finishWithResult(int resultCode, Intent resultIntent);

    void showDeleteActionDialog();

    Working getWorkingToDelete();

    void dismissDeleteActionDialog();

    void showIllnessActionDialog();

    Working getWorkingForIllness();

    void dismissIllnessActionDialog();

    void showVacationActionDialog();

    Working getWorkingForVacation();

    void dismissVacationActionDialog();
}
