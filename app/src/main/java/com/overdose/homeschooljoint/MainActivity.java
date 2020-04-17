package com.overdose.homeschooljoint;

import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.overdose.homeschooljoint.fragment.MenuFragment;
import com.overdose.homeschooljoint.fragment.MeFragment;
import com.overdose.homeschooljoint.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.rd_menu_menu)
    RadioButton rdMenuMenu;
    @BindView(R.id.rd_menu_me)
    RadioButton rdMenuMe;
    @BindView(R.id.rd_group)
    RadioGroup rdGroup;
    @BindView(R.id.bottom_layout)
    RelativeLayout bottomLayout;

    protected FragmentManager fragmentManager;
    private Fragment currentFragment;
    private Fragment menuFragment;
    private Fragment meFragment;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        initFragments();
        rdGroup.setOnCheckedChangeListener(this);
    }

    private void initFragments() {
        menuFragment = MenuFragment.newInstance();
        meFragment = MeFragment.newInstance();
        fragmentList.add(menuFragment);
        fragmentList.add(meFragment);


        if (fragmentList.size() > 0) {
            ActivityUtils.addFragmentToActivity(fragmentManager, fragmentList.get(0), R.id.container,
                    MeFragment.TAG);

            //设置默认第一个选中
            currentFragment = fragmentList.get(0);
            if (currentFragment instanceof MenuFragment) {
                rdMenuMenu.setChecked(true);
            } else if (currentFragment instanceof MeFragment) {
                rdMenuMe.setChecked(true);
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.rd_menu_menu:
                switchContent(currentFragment, menuFragment, MeFragment.TAG);
                break;

            case R.id.rd_menu_me:
                switchContent(currentFragment, meFragment, MenuFragment.TAG);
                break;


            default:
                break;
        }
    }

    //切换fragment
    private void switchContent(Fragment from, Fragment to, String tag) {
        if (currentFragment != to) {
            currentFragment = to;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.container, to, tag).commitAllowingStateLoss(); //
                // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
