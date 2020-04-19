package com.overdose.homeschooljoint.utils;

import com.overdose.homeschooljoint.BuildConfig;
import com.overdose.homeschooljoint.bean.RegisterBean;
import com.overdose.homeschooljoint.bean.RegisterRBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NetApi {

    String API_NODE_SERVER_URL = BuildConfig.API_SERVER_URL;

    /**
     * 我的UP排名
     */
    @GET("{role_name}/registere")
    Observable<RegisterRBean> queryRegister(@Path("role_name") String roleName,
                                            @Query("name") String name,
                                            @Query("code") String code,
                                            @Query("password") String password,
                                            @Query("classs") String classs,
                                            @Query("sex") String sex,
                                            @Query("coursecode") String courseCode,
                                            @Query("role") String role);


}
