package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseFragment;

public class UserCenterFragment extends BaseFragment {

    public static final String TAG = "CourseFragment";
    public static UserCenterFragment newInstance() {
        Bundle args = new Bundle();
        UserCenterFragment fragment = new UserCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_usercenter;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {

    }
}
