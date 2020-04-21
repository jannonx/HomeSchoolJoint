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

public class TeacherRatifyListAdapter extends BaseRecyclerAdapter<ToLeaveDataBean> {

    public TeacherRatifyListAdapter(Context context, @NonNull List<ToLeaveDataBean> listData,
                                    int layoutID) {
        super(context, listData, layoutID);
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ToLeaveDataBean item,
                                  int position) {

        TextView textView = holder.getView(R.id.tv_ratify_btn);
        holder.setText(R.id.tv_student, item.getStudentname());
        holder.setText(R.id.tv_content, item.getReason());
        holder.setText(R.id.tv_ratify_btn, item.getStatus() == 0 ? "批准" : "已批准");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null && "批准".equals(textView.getText().toString())) {
                    listener.onClick(position);
                }

            }
        });

    }

    public interface OnChildClickListener {
        void onClick(int position);
    }

    private OnChildClickListener listener;

    public void setListener(OnChildClickListener listener) {
        this.listener = listener;
    }
}
