package com.overdose.homeschooljoint;

import android.app.Application;
import android.os.Looper;

import com.overdose.homeschooljoint.base.api.ApiServiceComponent;
import com.overdose.homeschooljoint.base.api.ApiServiceModule;
import com.overdose.homeschooljoint.base.api.DaggerApiServiceComponent;

public class AppApplication extends Application {
    private static AppApplication application;//把application设置为静态对象
    private ApiServiceComponent apiServiceComponent;

    public static AppApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        apiServiceComponent = DaggerApiServiceComponent.builder()
                .apiServiceModule(new ApiServiceModule(this, "BuildConfig.SERVER_URL"))
                .build();
    }

    public ApiServiceComponent getApiServiceComponent() {
        return apiServiceComponent;
    }

    public static Looper getLooper() {
        return Looper.getMainLooper();
    }


}
