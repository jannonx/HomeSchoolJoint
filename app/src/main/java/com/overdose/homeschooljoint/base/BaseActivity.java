package com.overdose.homeschooljoint.base;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.overdose.homeschooljoint.AppApplication;
import com.overdose.homeschooljoint.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity {
    protected Unbinder unbinder;
    protected AppApplication application;
    protected FragmentManager fragmentManager;
    private CompositeDisposable mCompositeSubscription;
    private static final int REQUEST_CODE_PERMISSION = 9527;
    private AlertDialog alertDialog;
    private AlertDialog loadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getLayoutResId() != 0) {
            setContentView(getLayoutResId());
        }

        application = (AppApplication) getApplication();
        fragmentManager = getSupportFragmentManager();

        unbinder = ButterKnife.bind(this);
        if (isEventBusNeedRegister()) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 是否需要注册EventBus
     */
    protected boolean isEventBusNeedRegister() {
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
        {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    protected abstract int getLayoutResId();

    protected View getRootView() {
        return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
    }

    protected void setFullScreen() {
        getRootView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public void showToastTip(int resId) {
        Toast.makeText(getApplicationContext(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    public void showToastTip(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    public void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(disposable);
    }

    /**
     * RxJava取消注册，以避免内存泄露
     */
    public void releaseAllDisposable() {
        if (mCompositeSubscription != null && mCompositeSubscription.size() > 0) {
            mCompositeSubscription.dispose();
            mCompositeSubscription = null;
        }
    }

    /**
     * 某些功能需要确保先已经获得用户授权才可以实现，可以使用{@link BaseActivity#checkPermissions(String...)}
     * 然后才在这个回调中启动这些功能。
     */
    public void onAllPermissionsGranted() {

    }

    public void checkPermissions(String... permissions) {
        ArrayList<String> request = new ArrayList<>();
        for (String per : permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int result = checkSelfPermission(per);
                if (PackageManager.PERMISSION_GRANTED != result) {
                    request.add(per);
                }
            }
        }
        if (!request.isEmpty()) {
            String[] list = new String[request.size()];
            for (int i = 0; i < request.size(); i++) {
                list[i] = request.get(i);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(list, REQUEST_CODE_PERMISSION);
            }
        } else {
            onAllPermissionsGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isGranted = true;
        String[] rational = new String[grantResults.length];
        int index = 0;
        if (requestCode == REQUEST_CODE_PERMISSION) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    isGranted = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissions[i])) {
                            rational[index++] = permissions[i];
                        } else {
                            showToastTip("权限被限制，请到APP设置中授权。");
                            onBackPressed();
                            return;
                        }
                    }
                }
            }
        }
        if (isGranted) {
            onAllPermissionsGranted();
        } else {
            if (index > 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("敬请授权");
                StringBuilder sb = new StringBuilder();
                sb.append("为了使用此界面的功能，需要获得以下授权：\n");
                for (String temp : rational) {
                    sb.append(temp).append("\n");
                }
                sb.append("点击确定重新授权，点击取消退出此界面。");
                builder.setMessage(sb.toString());
                builder.setPositiveButton(getString(R.string.confirm),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(rational, REQUEST_CODE_PERMISSION);
                                }
                            }
                        });
                builder.setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                                onBackPressed();
                            }
                        });
                builder.setCancelable(false);
                alertDialog = builder.create();
                alertDialog.show();
            } else {
                showToastTip("权限被限制，请到APP设置中授权。");
                onBackPressed();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }


        if (isEventBusNeedRegister()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
