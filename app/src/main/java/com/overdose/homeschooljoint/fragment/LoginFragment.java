package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.util.TimeUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.ModifyPWActivity;
import com.overdose.homeschooljoint.activity.RegisterActivity;
import com.overdose.homeschooljoint.activity.UserCenterActivity;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.RegisterBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.AppClient;
import com.overdose.homeschooljoint.utils.LogUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

    public static final String TAG = "CourseFragment";
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pw)
    EditText etPw;

    @BindView(R.id.rb_student)
    RadioButton rbStudent;
    @BindView(R.id.rb_teacher)
    RadioButton rbTeacher;
    @BindView(R.id.rd_group)
    RadioGroup rdGroup;

    @BindView(R.id.login_ll)
    LinearLayout loginLl;

    private int checkedRbId = 0;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {
        rdGroup.setOnCheckedChangeListener(this);
        rbStudent.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.rb_student:
                LogUtils.showLog("rb_student");
                checkedRbId = 0;
                break;

            case R.id.rb_teacher:
                LogUtils.showLog("rb_teacher");
                checkedRbId = 1;
                break;


            default:
                break;
        }
    }


    @OnClick({R.id.tv_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                LogUtils.showLog("tv_login");
                break;
            case R.id.tv_register:
                RegisterActivity.start(getContext(),checkedRbId );
                break;
            default:
        }
    }


}
