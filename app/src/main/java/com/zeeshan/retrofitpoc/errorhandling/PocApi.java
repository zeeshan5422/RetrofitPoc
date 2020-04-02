package com.zeeshan.retrofitpoc.errorhandling;

import com.google.gson.GsonBuilder;
import com.zeeshan.retrofitpoc.gsonWrapperConvertor.GsonWrapperConvertorFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PocApi {
    private static final String TAG = PocApi.class.getSimpleName();

    private static IGlobalCallback iGlobalListener;

    private static Retrofit retrofit = null;

    public static Retrofit getApi() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(new PocErrorHandlingCallAdapterFactory(iGlobalListener))
//                .addConverterFactory(getConverterFactory())
                .addConverterFactory(getConverterFactory())
                .client(getHttpClient())
                .validateEagerly(true)
                .build();

        return retrofit;
    }

    private static Converter.Factory getConverterFactory() {
        return GsonConverterFactory.create(new GsonBuilder().serializeNulls().create());
    }

    private static Converter.Factory getWrapperConverterFactory() {
        return new GsonWrapperConvertorFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()));
    }

    // add your required interceptors here
    private static OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    public static void setGlobalListener(IGlobalCallback iGlobalListener) {
        PocApi.iGlobalListener = iGlobalListener;
    }
}
