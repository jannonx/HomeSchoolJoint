package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.BaseFragment;

public class ScoreFragment extends BaseFragment {

    public static final String TAG = "CourseFragment";
    public static ScoreFragment newInstance() {
        Bundle args = new Bundle();
        ScoreFragment fragment = new ScoreFragment();
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
