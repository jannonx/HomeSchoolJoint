package com.overdose.homeschooljoint.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseToolbarActivity;
import com.overdose.homeschooljoint.fragment.CourseFragment;
import com.overdose.homeschooljoint.fragment.HomeWorkTeacherFragment;
import com.overdose.homeschooljoint.utils.ActivityUtils;

public class HomeWorkTeacherActivity extends BaseToolbarActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, HomeWorkTeacherActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("布置作业");
        HomeWorkTeacherFragment fragment = HomeWorkTeacherFragment.newInstance();

        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.container,
                CourseFragment.TAG);
        ivMenu.setVisibility(View.VISIBLE);
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackByTeacherActivity.start(HomeWorkTeacherActivity.this);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base;
    }
}
