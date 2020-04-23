package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.overdose.homeschooljoint.AppApplication;
import com.overdose.homeschooljoint.MainActivity;
import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.LoginActivity;
import com.overdose.homeschooljoint.activity.RegisterActivity;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.RegisterQBean;
import com.overdose.homeschooljoint.bean.UserBean;
import com.overdose.homeschooljoint.utils.ActivityUtils;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.net.AppClient;
import com.overdose.homeschooljoint.utils.ConstantValue;
import com.overdose.homeschooljoint.utils.LogUtils;
import com.overdose.homeschooljoint.utils.ToastUtil;

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
                etAccount.setHint("请输入你的学号");
                break;

            case R.id.rb_teacher:
                LogUtils.showLog("rb_teacher");
                etAccount.setHint("请输入您的职工号");
                checkedRbId = 1;
                break;


            default:
                break;
        }
    }

    private void postLoginInfo() {
        String account = etAccount.getText().toString().trim();
        String pw = etPw.getText().toString().trim();
        LogUtils.showLog("RegisterQBean=" + account+"..pw="+pw);
        if (checkInfo(account) || checkInfo(pw)) {
            return;
        }
        RegisterQBean rBean = new RegisterQBean();
        rBean.setCode(account);
        rBean.setPassword(pw);
        rBean.setRole(checkedRbId == 0 ? "1" : "0");
        LogUtils.showLog("RegisterQBean=" + rBean.toString());
        login(rBean);
    }


    private boolean checkInfo(String info) {
        if (TextUtils.isEmpty(info)) {
            ToastUtil.showToast(getContext(), "信息不能为空");
            return true;
        } else {
            return false;
        }

    }

    @OnClick({R.id.tv_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                postLoginInfo();
                LogUtils.showLog("tv_login");
                break;
            case R.id.tv_register:
                RegisterActivity.start(getContext(), checkedRbId);
                break;
            default:
        }
    }

    public void login(RegisterQBean bean) {
        AppClient.subscribe(AppClient.getServerApi().requestLogin(bean.getCode(), bean.getPassword(), bean.getRole()),
                new ApiCallback<UserBean>() {
                    @Override
                    public void onSuccess(UserBean model) {
                        LogUtils.showLog("model=" + model.getData().getName());
                        if (model.getStatus() == 200) {
                            AppApplication.getInstance().saveCacheData(ConstantValue.USER_JSON_STRING,
                                    new Gson().toJson(model.getData()));
                            LogUtils.showLog("model=" + new Gson().toJson(model.getData()));
                            MainActivity.start(getContext());
                            ActivityUtils.removeActivity(LoginActivity.class.getSimpleName());
//                            ActivityUtils.removeActivity(RegisterActivity.class.getSimpleName());

                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        ToastUtil.showToast(getContext(), "注册失败!请重新注册!");
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }


}
