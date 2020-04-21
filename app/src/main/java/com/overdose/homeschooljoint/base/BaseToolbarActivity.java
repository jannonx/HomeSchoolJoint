package com.overdose.homeschooljoint.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.base.api.ApiServiceComponent;

import androidx.annotation.Nullable;
import butterknife.BindView;

public abstract class BaseToolbarActivity extends BaseActivity {

  @BindView(R.id.tv_title)
  protected TextView titleCenter;
  @BindView(R.id.iv_back)
  protected ImageView ivBack;
  @BindView(R.id.iv_menu)
  protected ImageView ivMenu;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initFragment(savedInstanceState);
    initToolBar();
  }

  protected void initToolBar() {
    ivBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  protected ApiServiceComponent getApiServiceComponent() {
    return application.getApiServiceComponent();
  }

  protected void setTitleCenter(String title) {
    titleCenter.setText(title);
  }

  protected abstract void initFragment(Bundle savedInstanceState);
}
