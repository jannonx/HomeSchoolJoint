package com.overdose.homeschooljoint.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.overdose.homeschooljoint.AppApplication;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment {
    protected View rootView;
    protected Unbinder mUnbinder;
    protected FragmentManager childManager;
    protected boolean isViewCreated;//界面是否创建完成
    protected boolean isVisible;//fragment是否可见
    private CompositeDisposable mCompositeSubscription;
    private String title;
    private AlertDialog loadingDialog;


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        reFreshData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(getLayoutResId(), container, false);
        }
        mUnbinder = ButterKnife.bind(this, rootView);
        childManager = getChildFragmentManager();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreated = true;
        initialization();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    public void showToastTip(int resId) {
        Toast.makeText(AppApplication.getInstance(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    public void showToastTip(String message) {
        Toast.makeText(AppApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }


    public synchronized void hideLoading() {

        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideLoading();
    }

    public abstract int getLayoutResId();

    public abstract void onUnBind();//fragment处于后台，进行一些对象的销毁操作

    //做一些初始化操作
    protected abstract void initialization();

    protected void reFreshUI() {
    }//fragment可见时刷新数据

    private void reFreshData() {
        if (isVisible && isViewCreated) {
            reFreshUI();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
        onUnBind();
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
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void handleException(Throwable throwable) {
        String message = throwable.getMessage();
        showToastTip(message);
    }

    /**
     * 普通跳转
     *
     * @param schemeUri 跳转作用域
     */
    public void startOtherActivity(String schemeUri) {
        startOtherActivity(schemeUri, null);
    }

    /**
     * 普通跳转
     *
     * @param schemeUri 跳转作用域
     * @param bundle    需要传递的参数
     */
    public void startOtherActivity(String schemeUri, Bundle bundle) {
        Intent dataIntent = new Intent();
        dataIntent.setAction(Intent.ACTION_VIEW);
        dataIntent.setData(Uri.parse(schemeUri));
        if (bundle != null) {
            dataIntent.putExtras(bundle);
        }
        startActivity(dataIntent);
    }

    /**
     * 普通跳转
     *
     * @param schemeUri   跳转作用域
     * @param requestCode 请求编码
     */
    public void startOtherActivityForResult(String schemeUri, int requestCode) {
        startOtherActivityForResult(schemeUri, requestCode, null);
    }

    /**
     * 普通跳转
     *
     * @param schemeUri  跳转作用域
     * @param resultCode 请求编码
     * @param bundle     需要传递的参数
     */
    public void startOtherActivityForResult(String schemeUri, int resultCode, Bundle bundle) {
        Intent dataIntent = new Intent();
        dataIntent.setAction(Intent.ACTION_VIEW);
        dataIntent.setData(Uri.parse(schemeUri));
        if (bundle != null) {
            dataIntent.putExtras(bundle);
        }
        startActivityForResult(dataIntent, resultCode);
    }
}
