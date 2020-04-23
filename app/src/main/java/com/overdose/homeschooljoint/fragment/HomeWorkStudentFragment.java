package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.view.View;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.FeedbackByStudentActivity;
import com.overdose.homeschooljoint.adpter.StudentHomeWorkListAdapter;
import com.overdose.homeschooljoint.base.BaseListFragment;
import com.overdose.homeschooljoint.bean.HomeWordQBean;
import com.overdose.homeschooljoint.bean.HomeWorkSBean;
import com.overdose.homeschooljoint.bean.UserDataBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.net.AppClient;
import com.overdose.homeschooljoint.utils.UserUtils;

import androidx.recyclerview.widget.LinearLayoutManager;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

public class HomeWorkStudentFragment extends BaseListFragment<HomeWordQBean> {

    public static final String TAG = "CourseFragment";

    public static HomeWorkStudentFragment newInstance() {
        Bundle args = new Bundle();
        HomeWorkStudentFragment fragment = new HomeWorkStudentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onUnBind() {

    }



    @Override
    protected void initView() {
        StudentHomeWorkListAdapter messageAdapter = new StudentHomeWorkListAdapter(getContext(), listData
                , R.layout.item_student_homework_list);
        adapter = new BaseRecyclerViewAdapter(messageAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FeedbackByStudentActivity.start(getContext(), listData.get(position));
            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        getHomeWorkList();
    }

    public void getHomeWorkList() {
        UserDataBean userData = UserUtils.getInstance().getUserData();
        AppClient.subscribe(AppClient.getServerApi().getHomeWorkListByStudent(userData.getCode()),
            new ApiCallback<HomeWorkSBean>() {
                @Override
                public void onSuccess(HomeWorkSBean model) {
                    if (model.getStatus() == 200) {
                            setListData(model.getData());

                    }
                }

                @Override
                public void onFailure(int code, String msg) {

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
