package com.overdose.homeschooljoint.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseToolbarActivity;
import com.overdose.homeschooljoint.fragment.CourseFragment;
import com.overdose.homeschooljoint.fragment.HomeWorkTeacherFragment;
import com.overdose.homeschooljoint.fragment.RegisterFragment;
import com.overdose.homeschooljoint.utils.ActivityUtils;
import com.overdose.homeschooljoint.utils.ConstantValue;

public class RegisterActivity extends BaseToolbarActivity {
    private String mTitle;

    public static void start(Context context, int title) {

        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(ConstantValue.TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        int checkedRbId = getIntent().getIntExtra(ConstantValue.TITLE, 0);
        setTitleCenter(checkedRbId == 0 ? "注册页面(学生)" : "注册页面(老师)");

        RegisterFragment fragment = RegisterFragment.newInstance(checkedRbId);

        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.container,
                CourseFragment.TAG);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base;
    }
}
