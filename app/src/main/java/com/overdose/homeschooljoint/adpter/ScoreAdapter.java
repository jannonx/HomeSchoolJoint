package com.overdose.homeschooljoint.adpter;

import android.content.Context;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.bean.ScoreDataBean;

import java.util.List;

import androidx.annotation.NonNull;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

public class ScoreAdapter extends BaseRecyclerAdapter<ScoreDataBean> {

  public ScoreAdapter(Context context, @NonNull List<ScoreDataBean> listData,
                      int layoutID) {
    super(context, listData, layoutID);
  }

  @Override
  protected void bindDataToView(BaseRecyclerViewHolder holder, ScoreDataBean item,
                                int position) {

    holder.setText(R.id.tv_course, item.getCoursename());
    holder.setText(R.id.tv_score, item.getScore());

  }
}
