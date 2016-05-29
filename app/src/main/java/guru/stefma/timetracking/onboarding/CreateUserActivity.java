package guru.stefma.timetracking.onboarding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import guru.stefma.timetracking.R;
import guru.stefma.timetracking.databinding.ActivityCreateUserBinding;

public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCreateUserBinding userBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_create_user);
        userBinding.setHandler(new CreateUserHandler(this));
    }
}
