package com.overdose.homeschooljoint.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseToolbarActivity;
import com.overdose.homeschooljoint.fragment.CourseFragment;
import com.overdose.homeschooljoint.fragment.RatifyFragment;
import com.overdose.homeschooljoint.utils.ActivityUtils;

public class RatifyActivity extends BaseToolbarActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, RatifyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("请假批准");
        RatifyFragment fragment = RatifyFragment.newInstance();

        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.container,
                CourseFragment.TAG);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base;
    }
}
