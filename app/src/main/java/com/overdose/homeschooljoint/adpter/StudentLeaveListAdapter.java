package com.overdose.homeschooljoint.adpter;

import android.content.Context;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.bean.ToLeaveDataBean;
import com.overdose.homeschooljoint.bean.UserDataBean;

import java.util.List;

import androidx.annotation.NonNull;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

public class StudentLeaveListAdapter extends BaseRecyclerAdapter<ToLeaveDataBean> {

    public StudentLeaveListAdapter(Context context, @NonNull List<ToLeaveDataBean> listData,
                                   int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ToLeaveDataBean item,
                                  int position) {

//        ImageView imageView = holder.getView(R.id.image);
//        Glide.with(context).load(item.getResId()).into(imageView);
        holder.setText(R.id.tv_opinion, item.getOpinion());
        holder.setText(R.id.tv_content, item.getReason());
        holder.setText(R.id.tv_teacher, item.getApprovername());
        holder.setText(R.id.tv_status, item.getStatusTxt());
    }
}
