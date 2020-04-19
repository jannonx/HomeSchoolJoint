package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.TeacherListActivity;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.SimpleRBean;
import com.overdose.homeschooljoint.bean.ToLeaveQBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.AppClient;
import com.overdose.homeschooljoint.utils.LogUtils;
import com.overdose.homeschooljoint.utils.RetrofitUtils;
import com.overdose.homeschooljoint.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ToLeaveFragment extends BaseFragment {

    public static final String TAG = "CourseFragment";
    @BindView(R.id.et_opinion)
    EditText etOpinion;
    @BindView(R.id.et_reason)
    EditText etReason;
    @BindView(R.id.tv_teacher)
    TextView tvTeacher;

    public static ToLeaveFragment newInstance() {
        Bundle args = new Bundle();
        ToLeaveFragment fragment = new ToLeaveFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_to_leave;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {

    }

    @OnClick({R.id.tv_leave_btn, R.id.iv_add_teacher})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_leave_btn:
                LogUtils.showLog("tv_register_btn");
                postLeaveInfo();
                break;
            case R.id.iv_add_teacher:
                LogUtils.showLog("iv_add_teacher");
                TeacherListActivity.start(getContext());
                break;
            default:
        }
    }


    private void postLeaveInfo() {
        String opinion = etOpinion.getText().toString().trim();
        String reason = etReason.getText().toString().trim();
        LogUtils.showLog("RegisterQBean=" + opinion + "..reason=" + reason);

        if (checkInfo(opinion) || checkInfo(reason)) {
            return;
        }
        if ("未选择老师".equals(tvTeacher.getText().toString())) {
            ToastUtil.showToast(getContext(), "请选择老师");
            return;
        }
        ToLeaveQBean rBean = new ToLeaveQBean();

        LogUtils.showLog("RegisterQBean=" + rBean.toString());
        leave(rBean);
    }


    private boolean checkInfo(String info) {
        if (TextUtils.isEmpty(info)) {
            ToastUtil.showToast(getContext(), "信息不能为空");
            return true;
        } else {
            return false;
        }
    }

    public void leave(ToLeaveQBean bean) {
        AppClient.subscribe(AppClient.getServerApi().leaveByStudent(RetrofitUtils.createRequestBody(bean)),
                new ApiCallback<SimpleRBean>() {
                    @Override
                    public void onSuccess(SimpleRBean model) {
                        if (model.getStatus() == 200) {
                            ToastUtil.showToast(getContext(), "申请成功!");
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        ToastUtil.showToast(getContext(), "申请失败!");
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }
}
