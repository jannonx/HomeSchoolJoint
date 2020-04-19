package com.overdose.homeschooljoint;

import android.app.Application;
import android.os.Looper;

import com.overdose.homeschooljoint.base.api.ApiServiceComponent;
import com.overdose.homeschooljoint.base.api.ApiServiceModule;
import com.overdose.homeschooljoint.base.api.DaggerApiServiceComponent;
import com.overdose.homeschooljoint.utils.SharedPreferencesUtils;

public class AppApplication extends Application {
    private static AppApplication application;//把application设置为静态对象
    private ApiServiceComponent apiServiceComponent;
    private SharedPreferencesUtils sharedPreferencesUtils;

    public static AppApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        sharedPreferencesUtils = SharedPreferencesUtils.getInstance(this);
        apiServiceComponent = DaggerApiServiceComponent.builder()
                .apiServiceModule(new ApiServiceModule(this, "BuildConfig.SERVER_URL"))
                .build();
    }

    public ApiServiceComponent getApiServiceComponent() {
        return apiServiceComponent;
    }

    /**
     * 采用andriod本身数据格式缓存数据
     * 后期可以更改成其他缓存
     */
    public void saveCacheData(String key, Object data) {
        saveCacheData(SharedPreferencesUtils.SP_NAME, key, data);
    }

    private void saveCacheData(final String fileName, final String key, final Object defaultObject) {
        sharedPreferencesUtils.saveData(fileName, key, defaultObject);
    }

    public Object getCacheData(String key, Object defaultObject) {
        return getCacheData(SharedPreferencesUtils.SP_NAME, key, defaultObject);
    }

    private Object getCacheData(final String fileName, final String key, final Object defaultObject) {
        return sharedPreferencesUtils.getData(fileName, key, defaultObject);
    }

}
