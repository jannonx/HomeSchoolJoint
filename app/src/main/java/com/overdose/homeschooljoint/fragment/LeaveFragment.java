package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseFragment;

public class LeaveFragment extends BaseFragment {

    public static final String TAG = "CourseFragment";
    public static LeaveFragment newInstance() {
        Bundle args = new Bundle();
        LeaveFragment fragment = new LeaveFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_emnu;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {

    }
}
