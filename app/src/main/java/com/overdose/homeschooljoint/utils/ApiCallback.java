package com.overdose.homeschooljoint.utils;



import com.overdose.homeschooljoint.utils.net.ServiceException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class ApiCallback<M> extends Subscriber<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(int code, String msg);

    public void onFinish() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            if (code == 504) {
                msg = "无法连接服务器，请检查网络";
            } else {
                msg = "网络异常，请稍后再试";
            }
            onFailure(code, msg);
        } else if (e instanceof ServiceException) {
            onFailure(-1, e.getMessage());
        } else {
            onFailure(-1, "网络异常，请稍后重试");
        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}
