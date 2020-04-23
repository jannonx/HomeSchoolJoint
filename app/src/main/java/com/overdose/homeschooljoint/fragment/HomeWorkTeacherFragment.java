package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.HomeWordQBean;
import com.overdose.homeschooljoint.bean.HomeWorkRBean;
import com.overdose.homeschooljoint.bean.UserDataBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.net.AppClient;
import com.overdose.homeschooljoint.utils.CalenderUtils;
import com.overdose.homeschooljoint.utils.LogUtils;
import com.overdose.homeschooljoint.utils.net.RetrofitUtils;
import com.overdose.homeschooljoint.utils.ToastUtil;
import com.overdose.homeschooljoint.utils.UserUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeWorkTeacherFragment extends BaseFragment {

    public static final String TAG = "CourseFragment";
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_leave_btn)
    TextView tvLeaveBtn;

    public static HomeWorkTeacherFragment newInstance() {
        Bundle args = new Bundle();
        HomeWorkTeacherFragment fragment = new HomeWorkTeacherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_post_homework_t;
    }

    @OnClick({R.id.tv_leave_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_leave_btn:
                LogUtils.showLog("tv_register_btn");
                postHomeWorkInfo();
                break;
            default:
        }
    }

    public void postHomeWorkInfo() {
        String content = etContent.getText().toString().trim();
//        String reason = etReason.getText().toString().trim();
//        LogUtils.showLog("RegisterQBean=" + opinion + "..reason=" + reason);

        if (checkInfo(content)) {
            return;
        }

        UserDataBean userData = UserUtils.getInstance().getUserData();
        String time = CalenderUtils.getInstance().toSmartFactoryDateStringFormat(new Date().getTime());
        HomeWordQBean rBean = new HomeWordQBean();
        rBean.setClasscode(userData.getClasscode());
        rBean.setInstructions(content);
        rBean.setTeachercode(userData.getCode());
        rBean.setTeachername(userData.getName());

        LogUtils.showLog("RegisterQBean=" + rBean.toString());
        postHomeWork(rBean);
    }

    private boolean checkInfo(String info) {
        if (TextUtils.isEmpty(info)) {
            ToastUtil.showToast(getContext(), "信息不能为空");
            return true;
        } else {
            return false;
        }
    }

    public void postHomeWork(HomeWordQBean bean) {
        AppClient.subscribe(AppClient.getServerApi().postHomeWorkByTeacher(RetrofitUtils.createRequestBody(bean)),
                new ApiCallback<HomeWorkRBean>() {
                    @Override
                    public void onSuccess(HomeWorkRBean model) {
                        if (model.getStatus() == 200) {
                            ToastUtil.showToast(getContext(), "发布成功!");
                            getActivity().finish();
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


    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {

    }
}
