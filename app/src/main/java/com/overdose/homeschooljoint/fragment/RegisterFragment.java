package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.overdose.homeschooljoint.AppApplication;
import com.overdose.homeschooljoint.MainActivity;
import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.LoginActivity;
import com.overdose.homeschooljoint.activity.RegisterActivity;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.RegisterQBean;
import com.overdose.homeschooljoint.bean.RegisterRBean;
import com.overdose.homeschooljoint.utils.ActivityUtils;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.AppClient;
import com.overdose.homeschooljoint.utils.ConstantValue;
import com.overdose.homeschooljoint.utils.LogUtils;
import com.overdose.homeschooljoint.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

    public static final String TAG = "CourseFragment";
    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.rb_man)
    RadioButton rbMan;
    @BindView(R.id.rb_female)
    RadioButton rbFemale;
    @BindView(R.id.rd_group)
    RadioGroup rdGroup;

    @BindView(R.id.et_pw)
    EditText etPw;
    @BindView(R.id.label_code)
    TextView labelCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.label_class)
    TextView labelClass;
    @BindView(R.id.et_class)
    EditText etClass;


    private int checkedRadioButtonId;
    private String roleName;
    private String role;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static RegisterFragment newInstance(int CheckedIndex) {
        Bundle args = new Bundle();
        args.putInt(ConstantValue.TITLE, CheckedIndex);
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_register;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {
        int checkedRbId = getArguments().getInt(ConstantValue.TITLE, 0);
        roleName = checkedRbId == 0 ? "student" : "teacher";
        role = checkedRbId == 0 ? "1" : "0";//role 1 是学生 role 0 是教师
        labelClass.setText(checkedRbId == 0 ? "班别" : "课程");
        labelCode.setText(checkedRbId == 0 ? "学号" : "职工号");

        rdGroup.setOnCheckedChangeListener(this);
        rbMan.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_man:
                LogUtils.showLog("rb_man");
                checkedRadioButtonId = 0;
                break;

            case R.id.rb_female:
                checkedRadioButtonId = 1;
                LogUtils.showLog("rb_female");
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.tv_register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_btn:
                postRegisterInfo();
                break;
            default:
        }
    }

    private void postRegisterInfo() {
        String name = etName.getText().toString().trim();
        String pw = etPw.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String sClass = etClass.getText().toString().trim();
        if (checkInfo(name) || checkInfo(pw) || checkInfo(code) || checkInfo(sClass)) {
            return;
        }
        RegisterQBean rBean = new RegisterQBean();
        rBean.setRoleName("student");
        rBean.setName(name);
        rBean.setSex(checkedRadioButtonId == 0 ? "男" : "女");
        rBean.setPassword(pw);
        rBean.setCode(code);
        rBean.setClasss(sClass);
        rBean.setRole(role);
        LogUtils.showLog("RegisterQBean=" + rBean.toString());
        register(rBean);
    }

    private boolean checkInfo(String info) {
        if (TextUtils.isEmpty(info)) {
            ToastUtil.showToast(getContext(), "信息不能为空");
            return true;
        } else {
            return false;
        }

    }

    public void register(RegisterQBean bean) {
        AppClient.subscribe(AppClient.getServerApi().queryRegister(bean.getRoleName(),
                bean.getName(), bean.getCode(), bean.getPassword(), bean.getClasss(), bean.getSex(), bean.getCoursecode(), bean.getRole()),
                new ApiCallback<RegisterRBean>() {


                    @Override
                    public void onSuccess(RegisterRBean model) {
                        LogUtils.showLog("model=" + model.getData().getName());
                        if (model.getStatus() == 200) {
                            AppApplication.getInstance().saveCacheData(ConstantValue.USER_JSON_STRING,
                                    new Gson().toJson(model.getData()));
                            MainActivity.start(getContext());
                            ActivityUtils.removeActivity(LoginActivity.class.getSimpleName());
                            ActivityUtils.removeActivity(RegisterActivity.class.getSimpleName());

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
