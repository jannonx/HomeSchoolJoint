package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.view.View;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.adpter.TeacherListAdapter;
import com.overdose.homeschooljoint.base.BaseListFragment;
import com.overdose.homeschooljoint.bean.SelectTeacherEvent;
import com.overdose.homeschooljoint.bean.TeacherListBean;
import com.overdose.homeschooljoint.bean.UserDataBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.net.AppClient;
import com.overdose.homeschooljoint.utils.UserUtils;

import org.greenrobot.eventbus.EventBus;

import androidx.recyclerview.widget.LinearLayoutManager;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

public class TeacherListFragment extends BaseListFragment<UserDataBean> {

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
        , R.layout.item_teachers_list);
    adapter = new BaseRecyclerViewAdapter(messageAdapter);
    adapter.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(View view, int position) {
        EventBus.getDefault().post(new SelectTeacherEvent(listData.get(position)));
        getActivity().finish();
      }
    });
    recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
    recycleView.setAdapter(adapter);
    getTeacherList();
  }

  public void getTeacherList() {
    UserDataBean userData = UserUtils.getInstance().getUserData();
    AppClient.subscribe(AppClient.getServerApi().getTeacherList(userData.getClasscode()),
        new ApiCallback<TeacherListBean>() {
          @Override
          public void onSuccess(TeacherListBean model) {
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
