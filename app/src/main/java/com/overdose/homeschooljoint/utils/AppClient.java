package com.overdose.homeschooljoint.utils;

import com.overdose.homeschooljoint.AppApplication;
import com.overdose.homeschooljoint.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AppClient {
    /**
     * GET_UPC地址
     */
    public static Retrofit mRetrofit;
    private static NetApi mNetApi;

    private static final int UPLOAD_DEFAULT_TIMEOUT = 60;//超时时长，单位：秒
    private static final int DEFAULT_TIMEOUT = 15;//超时时长，单位：秒
    private static final int CACHE_SIZE = 100 * 1024 * 1024;// 100 MiB

    public AppClient() {
        super();
    }


    public static NetApi getServerApi() {
        if (mNetApi == null) {
            mNetApi = getDefaultRetrofit().create(NetApi.class);
        }
        return mNetApi;
    }

    /**
     * 区块链节点/投票
     */
    public static Retrofit getDefaultRetrofit() {
        if (mRetrofit == null) {
            OkHttpClient okHttpClient = getDefaultOkHttpClient();
            mRetrofit = getDefaultRetrofitBuild(okHttpClient, BuildConfig.API_SERVER_URL);
        }
        return mRetrofit;
    }


    @NonNull
    public static Retrofit getDefaultRetrofitBuild(OkHttpClient okHttpClient, String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @NonNull
    public static OkHttpClient getDefaultOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }

        // 添加头部请求信息
//        builder.addInterceptor(new SecurityInterceptor());
        builder.addInterceptor(new NetworkCacheInterceptor());

        File cacheFile = new File(AppApplication.getInstance().getCacheDir(), "okhttp_cache");
        return builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
                readTimeout(UPLOAD_DEFAULT_TIMEOUT, TimeUnit.SECONDS).
                writeTimeout(UPLOAD_DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .sslSocketFactory(HttpsCertificateFactory.createSSLSocketFactory())
//                .hostnameVerifier(HttpsCertificateFactory.createHostnameVerifier())
//                .sslSocketFactory(HttpsFactroy.getSSLSocketFactory())
//                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                .retryOnConnectionFailure(true)
                .cache(new Cache(cacheFile, CACHE_SIZE))
                .build();
    }


    private static class NetworkCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtils.isConnected(AppApplication.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response originalResponse = chain.proceed(request);
            String cacheControl;
            if (NetUtils.isConnected(AppApplication.getInstance())) {
                // 有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                cacheControl = request.cacheControl().toString();
            } else {
                cacheControl = "public, only-if-cached, max-stale=" + Integer.MAX_VALUE;
            }
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        }
    }


    public static Observable.Transformer schedulersTransformer() {
        return new Observable.Transformer() {

            @Override
            public Object call(Object observable) {
                return ((Observable) observable).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> void subscribe(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
