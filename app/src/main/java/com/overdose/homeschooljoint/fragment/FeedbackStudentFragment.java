package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.TeacherListActivity;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.HomeWordQBean;
import com.overdose.homeschooljoint.bean.SimpleRBean;
import com.overdose.homeschooljoint.bean.ToLeaveQBean;
import com.overdose.homeschooljoint.bean.UserDataBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.AppClient;
import com.overdose.homeschooljoint.utils.CalenderUtils;
import com.overdose.homeschooljoint.utils.ConstantValue;
import com.overdose.homeschooljoint.utils.LogUtils;
import com.overdose.homeschooljoint.utils.RetrofitUtils;
import com.overdose.homeschooljoint.utils.ToastUtil;
import com.overdose.homeschooljoint.utils.UserUtils;

import java.io.Serializable;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedbackStudentFragment extends BaseFragment {

    public static final String TAG = "CourseFragment";
    @BindView(R.id.tv_teacher)
    TextView tvTeacher;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.et_reason)
    EditText etReason;
    @BindView(R.id.tv_leave_btn)
    TextView tvLeaveBtn;

    private HomeWordQBean homeData;

    public static FeedbackStudentFragment newInstance(HomeWordQBean bean) {
        Bundle args = new Bundle();
        args.putSerializable(ConstantValue.CONTENT, bean);
        FeedbackStudentFragment fragment = new FeedbackStudentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R.id.tv_leave_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_leave_btn:
                LogUtils.showLog("tv_register_btn");
                postFeedbackInfo();
                break;
            default:
        }
    }

    private void postFeedbackInfo() {
        String feedback = etReason.getText().toString().trim();
        if (checkInfo(feedback)) {
            return;
        }

        UserDataBean userData = UserUtils.getInstance().getUserData();
        String time = CalenderUtils.getInstance().toSmartFactoryDateStringFormat(new Date().getTime());
        HomeWordQBean rBean = new HomeWordQBean();
        rBean.setContent(feedback);
        rBean.setStudentscode(userData.getCode());
        rBean.setStudentname(userData.getName());
        rBean.setTeachername(homeData.getTeachername());
        rBean.setTeachercode(homeData.getTeachercode());

        LogUtils.showLog("RegisterQBean=" + rBean.toString());
        postFeedback(rBean);
    }

    private boolean checkInfo(String info) {
        if (TextUtils.isEmpty(info)) {
            ToastUtil.showToast(getContext(), "信息不能为空");
            return true;
        } else {
            return false;
        }
    }

    public void postFeedback(HomeWordQBean bean) {
        AppClient.subscribe(AppClient.getServerApi().postHomeWorkFeedBack(RetrofitUtils.createRequestBody(bean)),
                new ApiCallback<SimpleRBean>() {
                    @Override
                    public void onSuccess(SimpleRBean model) {
                        if (model.getStatus() == 200) {
                            ToastUtil.showToast(getContext(), "反馈成功!");
                            getActivity().finish();
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        ToastUtil.showToast(getContext(), "反馈失败!");
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_feedback_s;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {
        homeData = (HomeWordQBean) getArguments().getSerializable(ConstantValue.CONTENT);
        tvTeacher.setText(homeData.getTeachername());
        tvContent.setText(homeData.getInstructions());
    }
}
