package com.overdose.homeschooljoint.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseToolbarActivity;
import com.overdose.homeschooljoint.bean.HomeWordQBean;
import com.overdose.homeschooljoint.fragment.CourseFragment;
import com.overdose.homeschooljoint.fragment.FeedbackStudentFragment;
import com.overdose.homeschooljoint.fragment.FeedbackTeacherFragment;
import com.overdose.homeschooljoint.utils.ActivityUtils;
import com.overdose.homeschooljoint.utils.ConstantValue;

import java.io.Serializable;

public class FeedbackByStudentActivity extends BaseToolbarActivity {
    public static void start(Context context , HomeWordQBean bean) {
        Intent intent = new Intent(context, FeedbackByStudentActivity.class);
        intent.putExtra(ConstantValue.CONTENT,bean);
        context.startActivity(intent);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        setTitleCenter("问题反馈");
        HomeWordQBean data = (HomeWordQBean) getIntent().getSerializableExtra(ConstantValue.CONTENT);
        FeedbackStudentFragment fragment = FeedbackStudentFragment.newInstance(data);

        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.container,
                CourseFragment.TAG);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base;
    }
}
