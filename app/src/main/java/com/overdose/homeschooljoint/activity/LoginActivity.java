package com.overdose.homeschooljoint.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.fragment.CourseFragment;
import com.overdose.homeschooljoint.fragment.LoginFragment;
import com.overdose.homeschooljoint.utils.ActivityUtils;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        LoginFragment fragment = LoginFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container,
                CourseFragment.TAG);
    }


}
