package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.overdose.homeschooljoint.AppApplication;
import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.UserBean;
import com.overdose.homeschooljoint.utils.ConstantValue;

import butterknife.BindView;

public class UserCenterFragment extends BaseFragment {

    public static final String TAG = "CourseFragment";
    @BindView(R.id.label_name)
    TextView labelName;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.label_class)
    TextView labelClass;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.label_code)
    TextView labelCode;
    @BindView(R.id.tv_code)
    TextView tvCode;


    public static UserCenterFragment newInstance() {
        Bundle args = new Bundle();
        UserCenterFragment fragment = new UserCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_usercenter;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {
        String userStr =
                (String) AppApplication.getInstance().getCacheData(ConstantValue.USER_JSON_STRING, "");
        UserBean.DataBean userData = new Gson().fromJson(userStr, UserBean.DataBean.class);
        labelName.setText(userData.isStudent() ? "学生姓名" : "教师姓名");
        tvName.setText(userData.getName());
        tvSex.setText(userData.getSex());
        labelClass.setText(userData.isStudent() ? "班级" : "课程");
        tvClass.setText(userData.isStudent() ? userData.getClassname() : userData.getCoursename());
        labelCode.setText(userData.isStudent() ? "学号" : "教职号");
        tvCode.setText(userData.getCode());

    }
}
