package com.zeeshan.retrofitpoc.interceptors;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;
import java.net.URI;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class InternetConnectionInterceptor implements Interceptor {

    final int INTERNET_NOT_AVAILABLE = 909;

    protected abstract void onSuccess();

    public abstract void onFailure();

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (NetworkUtils.isConnected()) {
            onSuccess();
            return chain.proceed(chain.request());
        } else {
            onFailure();
            String responseString = "";
            // Get Request URI.
            /*final URI uri = chain.request().url().uri();
            // Get Query String.
            final String query = uri.getQuery();
            // Parse the Query String.
            final String[] parsedQuery = query.split("=");
            if(parsedQuery[0].equalsIgnoreCase("id") && parsedQuery[1].equalsIgnoreCase("1")) {
//                responseString = TEACHER_ID_1;
            }
            else if(parsedQuery[0].equalsIgnoreCase("id") && parsedQuery[1].equalsIgnoreCase("2")){
//                responseString = TEACHER_ID_2;
            }
            else {
                responseString = "";
            }*/
            return new Response.Builder()
                    .code(INTERNET_NOT_AVAILABLE)
                    .protocol(Protocol.HTTP_2)
                    .message("Internet is Not available")
                    .request(chain.request()).body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .build();
        }

/*        Request request = chain.request();
//        request = request.newBuilder().url("asdf")
//                .build()

        Response response = chain.proceed(request);

        if (response.code() != 200) {
            onFailure();
        } else {
            onSuccess();
        }*/


    }
}
