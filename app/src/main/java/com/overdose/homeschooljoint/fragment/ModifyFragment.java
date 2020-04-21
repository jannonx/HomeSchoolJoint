package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.overdose.homeschooljoint.AppApplication;
import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.ModifyPwQBean;
import com.overdose.homeschooljoint.bean.SimpleRBean;
import com.overdose.homeschooljoint.bean.UserBean;
import com.overdose.homeschooljoint.bean.UserDataBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.AppClient;
import com.overdose.homeschooljoint.utils.ConstantValue;
import com.overdose.homeschooljoint.utils.LogUtils;
import com.overdose.homeschooljoint.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyFragment extends BaseFragment {

    public static final String TAG = "CourseFragment";
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_confirm_pwd)
    EditText etConfirmPwd;
    @BindView(R.id.tv_modify_password)
    TextView tvModifyPassword;

    private String roleName;
    private UserDataBean userData;

    public static ModifyFragment newInstance() {
        Bundle args = new Bundle();
        ModifyFragment fragment = new ModifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_modify_pwd;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {
        String userStr =
                (String) AppApplication.getInstance().getCacheData(ConstantValue.USER_JSON_STRING, "");
        userData = new Gson().fromJson(userStr, UserDataBean.class);
//        roleName = userData.isStudent() ? "student" : "teacher";
    }

    @OnClick({R.id.tv_modify_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_modify_password:
                LogUtils.showLog("tv_modify_password");
                modifyPassword();
                break;

            default:
        }
    }

    private void modifyPassword() {
        String newPw = etNewPwd.getText().toString().trim();
        String cNewPw = etConfirmPwd.getText().toString().trim();
        if (checkInfo(newPw) || checkInfo(cNewPw)) {
            return;
        }
        if (!newPw.equals(cNewPw)) {
            ToastUtil.showToast(getContext(), "两次密码不一致");
            return;
        }

        ModifyPwQBean modifyPwQBean = new ModifyPwQBean();
        modifyPwQBean.setPassword(newPw);
        if (userData.isStudent()) {
            modifyPwQBean.setStudentCode(userData.getCode());
            modifyPwByStudent(modifyPwQBean);
        } else {
            modifyPwQBean.setTeacherCode(userData.getCode());
            modifyPwByTeacher(modifyPwQBean);
        }
    }

    private boolean checkInfo(String info) {
        if (TextUtils.isEmpty(info)) {
            ToastUtil.showToast(getContext(), "信息不能为空");
            return true;
        } else {
            return false;
        }

    }

    public void modifyPwByTeacher(ModifyPwQBean bean) {
        AppClient.subscribe(AppClient.getServerApi().modifyPwByTeacher(bean.getTeacherCode(), bean.getPassword()),
                new ApiCallback<SimpleRBean>() {
                    @Override
                    public void onSuccess(SimpleRBean model) {
                        if (model.getStatus() == 200) {
                            ToastUtil.showToast(getContext(), "修改密码成功!");
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        ToastUtil.showToast(getContext(), "修改密码失败!");
                    }

                    @Override
                    public void onFinish() {
                    }
                });
    }

    public void modifyPwByStudent(ModifyPwQBean bean) {
        AppClient.subscribe(AppClient.getServerApi().modifyPwByStudent(bean.getStudentCode(), bean.getPassword()),
                new ApiCallback<SimpleRBean>() {
                    @Override
                    public void onSuccess(SimpleRBean model) {
                        if (model.getStatus() == 200) {
                            ToastUtil.showToast(getContext(), "修改密码成功!");
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        ToastUtil.showToast(getContext(), "修改密码失败!");
                    }

                    @Override
                    public void onFinish() {
                    }
                });
    }
}
