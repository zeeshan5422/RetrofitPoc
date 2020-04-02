package com.zeeshan.retrofitpoc.interceptors;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class InternetTimerInterceptor implements Interceptor {

    public abstract void onSuccess();

    @Override
    public Response intercept(Chain chain) throws IOException {

        // TODO:: crate timer, start and end timer
       Request request = chain.request();

        Response response = chain.proceed(request);

        onSuccess();

        return response;

    }
}
