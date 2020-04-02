package com.zeeshan.retrofitpoc.nfs;

import com.google.gson.GsonBuilder;
import com.zeeshan.retrofitpoc.Api;
import com.zeeshan.retrofitpoc.errorhandling.PocErrorHandlingCallAdapterFactory;
import com.zeeshan.retrofitpoc.gsonWrapperConvertor.GsonWrapperConvertorFactory;
import com.zeeshan.retrofitpoc.gsonWrapperConvertor.WrapperResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NfsApiManager {

    private static final String TAG = Api.class.getSimpleName();

    private static final NfsApiManager mInstance = new NfsApiManager();
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static NfsApiManager getInstance() {
        return mInstance;
    }

    private Retrofit retrofit;

    private NfsApiManager() {
        createNetworkInstance();
    }

    private void createNetworkInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(getGsonConverterFactory())
                .addCallAdapterFactory(getErrorHandlingFactory())
                .client(getHttpClient())
                .build();
    }

    private CallAdapter.Factory getErrorHandlingFactory() {
        return new PocErrorHandlingCallAdapterFactory(null);
    }

    public Retrofit getApi() {
        if (retrofit == null) {
            createNetworkInstance();
        }
        return retrofit;
    }

    private static Converter.Factory getGsonConverterFactory() {
        return GsonConverterFactory.create(new GsonBuilder().serializeNulls().create());
    }

//    private static Converter.Factory getGsonWrapperConverterFactory() {
//        return new GsonWrapperConvertorFactory(WrapperResponse.class,GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()));
//    }

    // add your required interceptors here
    private static OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
//                .addInterceptor(addConnectionInterceptor())
//                .addInterceptor(addResponseInterceptor())
//                .addNetworkInterceptor(addInternetTimerInterceptor())
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

}
