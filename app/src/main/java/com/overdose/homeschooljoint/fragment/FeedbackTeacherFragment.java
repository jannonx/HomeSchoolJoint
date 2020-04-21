package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.view.View;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.FeedbackByStudentActivity;
import com.overdose.homeschooljoint.adpter.StudentHomeWorkListAdapter;
import com.overdose.homeschooljoint.adpter.TeacherFeedbackListAdapter;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.base.BaseListFragment;
import com.overdose.homeschooljoint.bean.HomeWordQBean;
import com.overdose.homeschooljoint.bean.HomeWorkSBean;
import com.overdose.homeschooljoint.bean.UserDataBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.AppClient;
import com.overdose.homeschooljoint.utils.UserUtils;

import androidx.recyclerview.widget.LinearLayoutManager;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

public class FeedbackTeacherFragment extends BaseListFragment<HomeWordQBean> {

    public static final String TAG = "CourseFragment";
    public static FeedbackTeacherFragment newInstance() {
        Bundle args = new Bundle();
        FeedbackTeacherFragment fragment = new FeedbackTeacherFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onUnBind() {

    }


    @Override
    protected void initView() {
        TeacherFeedbackListAdapter messageAdapter = new TeacherFeedbackListAdapter(getContext(), listData
                , R.layout.item_homework_feedback_list_s);
        adapter = new BaseRecyclerViewAdapter(messageAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                FeedbackByStudentActivity.start(getContext(), listData.get(position));
            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        getFeedbackList();
    }

    public void getFeedbackList() {
        UserDataBean userData = UserUtils.getInstance().getUserData();
        AppClient.subscribe(AppClient.getServerApi().getHomeWorkFeedBackList(userData.getCode()),
                new ApiCallback<HomeWorkSBean>() {
                    @Override
                    public void onSuccess(HomeWorkSBean model) {
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
