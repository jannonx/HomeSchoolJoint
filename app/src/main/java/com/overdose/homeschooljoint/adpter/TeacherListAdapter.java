package com.overdose.homeschooljoint.adpter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.bean.UserBean;
import com.overdose.homeschooljoint.bean.UserDataBean;


import java.util.List;

import androidx.annotation.NonNull;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

public class TeacherListAdapter extends BaseRecyclerAdapter<UserDataBean> {

    public TeacherListAdapter(Context context, @NonNull List<UserDataBean> listData,
                              int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, UserDataBean item,
                                  int position) {

//        ImageView imageView = holder.getView(R.id.image);
//        Glide.with(context).load(item.getResId()).into(imageView);
        holder.setText(R.id.tv_name, item.getName());
        holder.setText(R.id.tv_course, item.getCoursename());
    }
}
