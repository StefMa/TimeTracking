package guru.stefma.timetracking.onboarding;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

import guru.stefma.restapi.ApiHelper;
import guru.stefma.restapi.objects.Token;
import guru.stefma.restapi.objects.user.CreateUser;
import guru.stefma.restapi.objects.user.UserResult;
import guru.stefma.timetracking.R;
import guru.stefma.timetracking.main.MainActivity;
import guru.stefma.timetracking.settings.SettingsManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserHandler {

    private String mUsername;

    @SuppressWarnings("unused")
    public TextWatcher mUsernameChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            mUsername = editable.toString();
        }
    };

    public CreateUserHandler(Context activityContext) {
        String token = SettingsManager.getUserToken(activityContext);
        if (!TextUtils.isEmpty(token)) {
            Intent intent = MainActivity.newInstance(activityContext);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            activityContext.startActivity(intent);
        }
    }

    @SuppressWarnings("unused")
    public void onCreateUserClick(final View view) {
        final Context context = view.getContext();
        final ProgressDialog dialog = ProgressDialog.show(context, "", "");
        new ApiHelper().createUser(new CreateUser(mUsername), new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                if (response.isSuccessful()) {
                    UserResult userResult = response.body();
                    if (Objects.equals(userResult.getResult(), UserResult.RESULT_ALREADY_EXIST)) {
                        Snackbar.make(view, context.getString(R.string.create_user_already_exist), Snackbar.LENGTH_LONG).show();
                        dialog.dismiss();
                    } else if (Objects.equals(userResult.getResult(), UserResult.RESULT_OK)) {
                        SettingsManager.saveUserToken(context, userResult.getToken());
                        context.startActivity(MainActivity.newInstance(context));
                        dialog.dismiss();
                    }
                } else {
                    Snackbar.make(view, context.getString(R.string.default_error), Snackbar.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                t.printStackTrace();
                Snackbar.make(view, context.getString(R.string.default_error), Snackbar.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }

    @SuppressWarnings("unused")
    public void onAlreadyHaveAccountClick(final View view) {
        final Context context = view.getContext();
        View dialogView = View.inflate(context, R.layout.dialog_edittext, null);
        final EditText dialogToken = (EditText) dialogView.findViewById(R.id.dialog_edittext);
        new AlertDialog.Builder(context, R.style.Dialog)
                .setTitle(context.getString(R.string.login))
                .setView(dialogView)
                .setPositiveButton(context.getString(R.string.login), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new ApiHelper().loginUser(new Token(dialogToken.getText().toString()),
                                new Callback<UserResult>() {
                                    @Override
                                    public void onResponse(Call<UserResult> call,
                                                           Response<UserResult> response) {
                                        if (response.isSuccessful()) {
                                            UserResult result = response.body();
                                            if (result.getResult().equals(UserResult.RESULT_OK)) {
                                                SettingsManager.saveUserToken(context, result.getToken());
                                                context.startActivity(MainActivity.newInstance(context));
                                            } else {
                                                Snackbar.make(view, context.getString(R.string.default_error),
                                                        Snackbar.LENGTH_LONG).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<UserResult> call, Throwable t) {
                                        t.printStackTrace();
                                        Snackbar.make(view, context.getString(R.string.default_error),
                                                Snackbar.LENGTH_LONG).show();
                                    }
                                });
                    }
                })
                .create().show();
    }

}
