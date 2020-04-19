package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.view.View;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.CourseActivity;
import com.overdose.homeschooljoint.activity.HomeWorkStudentActivity;
import com.overdose.homeschooljoint.activity.HomeWorkTeacherActivity;
import com.overdose.homeschooljoint.activity.RatifyActivity;
import com.overdose.homeschooljoint.activity.ScoreActivity;
import com.overdose.homeschooljoint.activity.ToLeaveActivity;
import com.overdose.homeschooljoint.adpter.MenuAdapter;
import com.overdose.homeschooljoint.adpter.TeacherListAdapter;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.base.BaseListFragment;
import com.overdose.homeschooljoint.bean.UserBean;
import com.overdose.homeschooljoint.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

public class TeacherListFragment extends BaseListFragment<UserBean.DataBean> {

    public static final String TAG = "CourseFragment";

    public static TeacherListFragment newInstance() {
        Bundle args = new Bundle();
        TeacherListFragment fragment = new TeacherListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onUnBind() {

    }


    @Override
    protected void initView() {
        TeacherListAdapter messageAdapter = new TeacherListAdapter(getContext(), listData
                , R.layout.item_spinner);
        adapter = new BaseRecyclerViewAdapter(messageAdapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);

        initListData();
    }

    private void initListData() {
        List<UserBean.DataBean> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UserBean.DataBean bean = new UserBean.DataBean();
            userList.add(bean);
        }
        setListData(userList);
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
