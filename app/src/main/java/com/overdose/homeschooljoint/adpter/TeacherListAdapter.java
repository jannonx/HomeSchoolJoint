package com.overdose.homeschooljoint.adpter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.bean.UserBean;


import java.util.List;

import androidx.annotation.NonNull;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

public class TeacherListAdapter extends BaseRecyclerAdapter<UserBean.DataBean> {

    public TeacherListAdapter(Context context, @NonNull List<UserBean.DataBean> listData,
                              int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, UserBean.DataBean item,
                                  int position) {

//        ImageView imageView = holder.getView(R.id.image);
//        Glide.with(context).load(item.getResId()).into(imageView);
//        holder.setText(R.id.title, item.getTitle());
    }
}
