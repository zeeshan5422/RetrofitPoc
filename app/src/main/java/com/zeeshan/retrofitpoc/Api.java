package com.zeeshan.retrofitpoc;

import com.google.gson.GsonBuilder;
import com.zeeshan.retrofitpoc.listeners.InternetConnectionListener;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String TAG = Api.class.getSimpleName();

    private static InternetConnectionListener mConnectionListener;

    private static Retrofit retrofit = null;

    public static Retrofit getApi() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(getConverterFactory())
                .client(getHttpClient())
                .validateEagerly(true)
                .build();

        return retrofit;
    }

    private static Converter.Factory getConverterFactory() {
//        return new WrapperConverterFactory(GsonConverterFactory.create());
        return GsonConverterFactory.create(new GsonBuilder().serializeNulls().create());
    }

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

   /* private static ResponseInterceptor addResponseInterceptor() {
        return new ResponseInterceptor() {


            @Override
            void onSuccess() {
                Log.v(TAG, "response interceptor :: onSuccess");
            }

            @Override
            void onFailure() {
                Log.v(TAG, "response interceptor :: onFailure");
            }
        };
    }

    private static InternetTimerInterceptor addInternetTimerInterceptor() {
        return new InternetTimerInterceptor() {
            @Override
            void onSuccess() {
                Log.v(TAG, "TImer interceptor :: onSuccess");
            }
        };
    }

    private static InternetConnectionInterceptor addConnectionInterceptor() {
        return new InternetConnectionInterceptor() {
            @Override
            void onSuccess() {
                mConnectionListener.onInternetAvailable();
            }

            @Override
            void onFailure() {
                mConnectionListener.onInternetUnavailable();
            }
        };
    }*/

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    public static void setInternetConnectionListener(InternetConnectionListener mConnectionListener) {
        Api.mConnectionListener = mConnectionListener;
    }
}
