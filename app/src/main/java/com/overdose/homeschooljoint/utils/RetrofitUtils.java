package com.overdose.homeschooljoint.utils;


import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RetrofitUtils {

    public static RequestBody createRequestBody(String body) {
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        return RequestBody.create(mediaType, body);
    }

    public static RequestBody createRequestBody(Object object) {
        String body = new Gson().toJson(object);
       return createRequestBody(body);
    }



}
