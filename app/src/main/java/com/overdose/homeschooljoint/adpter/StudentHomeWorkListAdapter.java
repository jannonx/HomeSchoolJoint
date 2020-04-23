package com.overdose.homeschooljoint.adpter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.bean.HomeWordQBean;
import com.overdose.homeschooljoint.bean.ToLeaveDataBean;

import java.util.List;

import androidx.annotation.NonNull;
import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

public class StudentHomeWorkListAdapter extends BaseRecyclerAdapter<HomeWordQBean> {

    public StudentHomeWorkListAdapter(Context context, @NonNull List<HomeWordQBean> listData,
                                      int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, HomeWordQBean item,
                                  int position) {


//
        holder.setText(R.id.tv_teacher, item.getTeachername());
        holder.setText(R.id.tv_content, item.getInstructions());



    }


}
