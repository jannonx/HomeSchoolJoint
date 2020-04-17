package com.overdose.homeschooljoint.adpter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.bean.MenuBean;

import java.util.List;

import androidx.annotation.NonNull;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

public class MenuAdapter extends BaseRecyclerAdapter<MenuBean> {

    public MenuAdapter(Context context, @NonNull List<MenuBean> listData,
                       int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, MenuBean item,
                                  int position) {

        ImageView imageView = holder.getView(R.id.image);
        Glide.with(context).load(item.getResId()).into(imageView);
        holder.setText(R.id.title, item.getTitle());
    }
}
