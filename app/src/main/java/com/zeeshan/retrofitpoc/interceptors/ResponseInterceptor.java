package com.zeeshan.retrofitpoc.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class ResponseInterceptor implements Interceptor {

    public abstract void onSuccess();

    public abstract void onFailure();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        request = request.newBuilder().url("asdf")
//                .build();

        Response response = chain.proceed(request);

        if (response.code() != 200) {
            onFailure();
        } else {
            onSuccess();
        }
        return response;
    }
}
