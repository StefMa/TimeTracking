package guru.stefma.timetracking.onboarding;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import guru.stefma.restapi.objects.user.UserResult;
import guru.stefma.timetracking.R;
import guru.stefma.timetracking.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    public static Intent newInstance(Context context, UserResult token) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.putExtra(Welcome.KEY_USER_TOKEN, token.getToken());
        intent.putExtra(Welcome.KEY_USERNAME, token.getUsername());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWelcomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        binding.setWelcome(new Welcome(this, getIntent().getExtras()));
    }

}
