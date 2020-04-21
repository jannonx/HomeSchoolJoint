package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.overdose.homeschooljoint.AppApplication;
import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.CourseActivity;
import com.overdose.homeschooljoint.activity.HomeWorkStudentActivity;
import com.overdose.homeschooljoint.activity.HomeWorkTeacherActivity;
import com.overdose.homeschooljoint.activity.ToLeaveActivity;
import com.overdose.homeschooljoint.activity.RatifyActivity;
import com.overdose.homeschooljoint.activity.ScoreActivity;
import com.overdose.homeschooljoint.adpter.MenuAdapter;
import com.overdose.homeschooljoint.base.BaseListFragment;
import com.overdose.homeschooljoint.bean.MenuBean;
import com.overdose.homeschooljoint.bean.UserBean;
import com.overdose.homeschooljoint.bean.UserDataBean;
import com.overdose.homeschooljoint.utils.ConstantValue;
import com.overdose.homeschooljoint.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

public class MenuFragment extends BaseListFragment<MenuBean> {
  public static final String TAG = "MeFragment";
  int[] resIdSArr = new int[]{R.mipmap.ic_menu_course, R.mipmap.ic_menu_score,
      R.mipmap.ic_menu_leave, R.mipmap.ic_menu_homework_s};
  String[] titleSArr = new String[]{"课程表", "成绩查询", "请假申请", "每日任务"};

  int[] resIdTAss = new int[]{R.mipmap.ic_menu_ratify, R.mipmap.ic_menu_homework_t};
  String[] titleTArr = new String[]{"请假批准", "布置任务"};

  private boolean isTeacher = false;
  List<MenuBean> mDataList = new ArrayList<>();

  private UserDataBean dataBean;

  public static MenuFragment newInstance() {
    Bundle args = new Bundle();
    MenuFragment fragment = new MenuFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public void onUnBind() {

  }


  @Override
  protected void initView() {
    MenuAdapter messageAdapter = new MenuAdapter(getContext(), listData
        , R.layout.item_menu);
    adapter = new BaseRecyclerViewAdapter(messageAdapter);
    adapter.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(View view, int position) {
        String title = listData.get(position).getTitle();
        switch (title) {
          case ConstantValue.ITEM_MENU_COURSE:
            CourseActivity.start(getContext());
            break;
          case ConstantValue.ITEM_MENU_SCORE:
            ScoreActivity.start(getContext());
            break;
          case ConstantValue.ITEM_MENU_LEAVE:
            ToLeaveActivity.start(getContext());
            break;
          case ConstantValue.ITEM_MENU_HOMEWORK_S:
            HomeWorkStudentActivity.start(getContext());
            break;
          case ConstantValue.ITEM_MENU_RATIFY:
            RatifyActivity.start(getContext());
            break;
          case ConstantValue.ITEM_MENU_HOMEWORK_T:
            HomeWorkTeacherActivity.start(getContext());
            break;
        }
      }
    });
    recycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    recycleView.setAdapter(adapter);

    initData();
  }

  private void initData() {
    dataBean = UserUtils.getInstance().getUserData();

    List<MenuBean> mTempList = new ArrayList<>();
    if (dataBean.isStudent()) {
      for (int i = 0; i < resIdSArr.length; i++) {
        MenuBean menuBean = new MenuBean(titleSArr[i], resIdSArr[i]);
        mTempList.add(menuBean);
      }

    } else {
      for (int i = 0; i < resIdTAss.length; i++) {
        MenuBean menuBean = new MenuBean(titleTArr[i], resIdTAss[i]);
        mTempList.add(menuBean);
      }
    }

    Log.e("MenuFragment", "initData: " + mDataList.size());

    setListData(mTempList);
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
