package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.adpter.ScoreAdapter;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.ScoreDataBean;
import com.overdose.homeschooljoint.bean.ScoreRBean;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.net.AppClient;
import com.overdose.homeschooljoint.utils.LogUtils;
import com.overdose.homeschooljoint.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import butterknife.BindView;
import butterknife.OnClick;
import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

public class ScoreFragment extends BaseFragment {

  public static final String TAG = "CourseFragment";
  @BindView(R.id.et_opinion)
  EditText mEtOpinion;
  @BindView(R.id.base_recycleView)
  BaseRecyclerView mBaseRecycleView;

  private BaseRecyclerViewAdapter adapter;

  private List<ScoreDataBean> listData = new ArrayList<>();

  public static ScoreFragment newInstance() {
    Bundle args = new Bundle();
    ScoreFragment fragment = new ScoreFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getLayoutResId() {
    return R.layout.fragment_score;
  }

  @Override
  public void onUnBind() {

  }

  @Override
  protected void initialization() {
    ScoreAdapter messageAdapter = new ScoreAdapter(getContext(), listData
        , R.layout.item_score);
    adapter = new BaseRecyclerViewAdapter(messageAdapter);
    adapter.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(View view, int position) {

      }
    });


    mBaseRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 4));
    mBaseRecycleView.setAdapter(adapter);
  }


  @OnClick({R.id.tv_score_btn})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.tv_score_btn:
        LogUtils.showLog("tv_score_btn");
        postScoreInfo();
        break;
      default:
    }
  }


  private void postScoreInfo() {
    String opinion = mEtOpinion.getText().toString().trim();

    if (checkInfo(opinion)) {
      return;
    }
    getScoreList(opinion);
  }

  private boolean checkInfo(String info) {
    if (TextUtils.isEmpty(info)) {
      ToastUtil.showToast(getContext(), "信息不能为空");
      return true;
    } else {
      return false;
    }
  }

  public void getScoreList(String studentCode) {
    AppClient.subscribe(AppClient.getServerApi().getScoreList(studentCode),
        new ApiCallback<ScoreRBean>() {


          @Override
          public void onSuccess(ScoreRBean model) {
            if (model.getStatus() == 200) {
              LogUtils.showLog("model=" + new Gson().toJson(model.getData()));
              listData.clear();
              listData.addAll(model.getData());
              adapter.refreshData();
            }
          }

          @Override
          public void onFailure(int code, String msg) {
            ToastUtil.showToast(getContext(), "查询失败！");
          }

          @Override
          public void onFinish() {

          }
        });
  }
}
