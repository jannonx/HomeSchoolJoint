package com.overdose.homeschooljoint.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseToolbarActivity;
import com.overdose.homeschooljoint.fragment.CourseFragment;
import com.overdose.homeschooljoint.fragment.LeaveFragment;
import com.overdose.homeschooljoint.utils.ActivityUtils;

public class LeaveActivity extends BaseToolbarActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, LeaveActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("请假申请");
        LeaveFragment fragment = LeaveFragment.newInstance();

        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.container,
                CourseFragment.TAG);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base;
    }
}
