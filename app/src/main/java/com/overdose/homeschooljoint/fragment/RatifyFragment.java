package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.view.View;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.FeedbackByStudentActivity;
import com.overdose.homeschooljoint.adpter.StudentHomeWorkListAdapter;
import com.overdose.homeschooljoint.adpter.TeacherRatifyListAdapter;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.base.BaseListFragment;
import com.overdose.homeschooljoint.bean.HomeWorkSBean;
import com.overdose.homeschooljoint.bean.SimpleRBean;
import com.overdose.homeschooljoint.bean.ToLeaveDataBean;
import com.overdose.homeschooljoint.bean.ToLeaveRBean;
import com.overdose.homeschooljoint.bean.ToLeaveTBean;
import com.overdose.homeschooljoint.bean.UserDataBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.AppClient;
import com.overdose.homeschooljoint.utils.ToastUtil;
import com.overdose.homeschooljoint.utils.UserUtils;

import androidx.recyclerview.widget.LinearLayoutManager;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

public class RatifyFragment extends BaseListFragment<ToLeaveDataBean> {

    public static final String TAG = "CourseFragment";
    private UserDataBean userData;

    public static RatifyFragment newInstance() {
        Bundle args = new Bundle();
        RatifyFragment fragment = new RatifyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onUnBind() {

    }


    @Override
    protected void initView() {
        userData = UserUtils.getInstance().getUserData();
        TeacherRatifyListAdapter messageAdapter = new TeacherRatifyListAdapter(getContext(), listData
                , R.layout.item_teacher_ratify);
        adapter = new BaseRecyclerViewAdapter(messageAdapter);

        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        getRatifyList();

        messageAdapter.setListener(new TeacherRatifyListAdapter.OnChildClickListener() {
            @Override
            public void onClick(int position) {
                getRatifyLeave(listData.get(position));
            }
        });
    }

    public void getRatifyList() {

        AppClient.subscribe(AppClient.getServerApi().getTeacherRatifyList(userData.getCode()),
                new ApiCallback<ToLeaveRBean>() {
                    @Override
                    public void onSuccess(ToLeaveRBean model) {
                        if (model.getStatus() == 200) {
                            setListData(model.getData());
//                      ToastUtil.showToast(getContext(), "修改密码成功!");
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
//                  ToastUtil.showToast(getContext(), "修改密码失败!");
                    }

                    @Override
                    public void onFinish() {
                    }
                });
    }

    public void getRatifyLeave(ToLeaveDataBean bean) {
        UserDataBean userData = UserUtils.getInstance().getUserData();
        AppClient.subscribe(AppClient.getServerApi().ratifyStudentLeave(bean.getId() + "", "1"),
                new ApiCallback<ToLeaveTBean>() {
                    @Override
                    public void onSuccess(ToLeaveTBean model) {
                        if (model.getStatus() == 200) {
                            ToastUtil.showToast(getContext(), "批准成功!");
                            getRatifyList();
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        ToastUtil.showToast(getContext(), "批准失败!");
                    }

                    @Override
                    public void onFinish() {
                    }
                });
    }


    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }
}
