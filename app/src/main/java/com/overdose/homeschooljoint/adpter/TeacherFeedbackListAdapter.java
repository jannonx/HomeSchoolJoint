package com.overdose.homeschooljoint.adpter;

import android.content.Context;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.bean.HomeWordQBean;

import java.util.List;

import androidx.annotation.NonNull;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

public class TeacherFeedbackListAdapter extends BaseRecyclerAdapter<HomeWordQBean> {

    public TeacherFeedbackListAdapter(Context context, @NonNull List<HomeWordQBean> listData,
                                      int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, HomeWordQBean item,
                                  int position) {

//        ImageView imageView = holder.getView(R.id.image);
//        Glide.with(context).load(item.getResId()).into(imageView);
        holder.setText(R.id.tv_student, item.getTeachername());
        holder.setText(R.id.tv_feedback_content, item.getContent());
//        holder.setText(R.id.tv_teacher, item.getApprovername());
//        holder.setText(R.id.tv_status, item.getStatusTxt());
    }
}
