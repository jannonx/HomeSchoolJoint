package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.view.View;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.adpter.StudentLeaveListAdapter;
import com.overdose.homeschooljoint.adpter.TeacherListAdapter;
import com.overdose.homeschooljoint.base.BaseListFragment;
import com.overdose.homeschooljoint.bean.SelectTeacherEvent;
import com.overdose.homeschooljoint.bean.StudentLeaveListBean;
import com.overdose.homeschooljoint.bean.TeacherListBean;
import com.overdose.homeschooljoint.bean.ToLeaveDataBean;
import com.overdose.homeschooljoint.bean.UserDataBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.AppClient;
import com.overdose.homeschooljoint.utils.UserUtils;

import org.greenrobot.eventbus.EventBus;

import androidx.recyclerview.widget.LinearLayoutManager;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

public class StudentLeaveListFragment extends BaseListFragment<ToLeaveDataBean> {

  public static final String TAG = "CourseFragment";

  public static StudentLeaveListFragment newInstance() {
    Bundle args = new Bundle();
    StudentLeaveListFragment fragment = new StudentLeaveListFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public void onUnBind() {

  }


  @Override
  protected void initView() {
    StudentLeaveListAdapter messageAdapter = new StudentLeaveListAdapter(getContext(), listData
        , R.layout.item_student_leave_list);
    adapter = new BaseRecyclerViewAdapter(messageAdapter);
    adapter.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(View view, int position) {
//        EventBus.getDefault().post(new SelectTeacherEvent(listData.get(position)));
//        getActivity().finish();
      }
    });
    recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
    recycleView.setAdapter(adapter);
    getTeacherList();
  }

  public void getTeacherList() {
    UserDataBean userData = UserUtils.getInstance().getUserData();
    AppClient.subscribe(AppClient.getServerApi().getLeaveListByStudent(userData.getCode()),
        new ApiCallback<StudentLeaveListBean>() {
          @Override
          public void onSuccess(StudentLeaveListBean model) {
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
