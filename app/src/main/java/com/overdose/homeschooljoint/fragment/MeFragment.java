package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.view.View;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.ModifyPWActivity;
import com.overdose.homeschooljoint.activity.UserCenterActivity;
import com.overdose.homeschooljoint.base.BaseFragment;

import butterknife.OnClick;

public class MeFragment extends BaseFragment {
    public static final String TAG = "MenuFragment";

    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {

    }

    @OnClick({R.id.ll_user_center, R.id.ll_modify_pw, R.id.ll_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_user_center:
                UserCenterActivity.start(getContext());
                break;
            case R.id.ll_modify_pw:
                ModifyPWActivity.start(getContext());
                break;
            case R.id.ll_logout:

                break;
            default:
        }
    }
}
