package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseFragment;

public class ModifyFragment extends BaseFragment {

    public static final String TAG = "CourseFragment";
    public static ModifyFragment newInstance() {
        Bundle args = new Bundle();
        ModifyFragment fragment = new ModifyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_modify_pwd;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {

    }
}
