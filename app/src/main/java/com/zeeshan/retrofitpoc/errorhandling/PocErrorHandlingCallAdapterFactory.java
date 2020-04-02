package com.zeeshan.retrofitpoc.errorhandling;

import androidx.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class PocErrorHandlingCallAdapterFactory extends CallAdapter.Factory {

    private IGlobalCallback iGlobalListener;

    public PocErrorHandlingCallAdapterFactory(IGlobalCallback iGlobalListener) {
        this.iGlobalListener = iGlobalListener;
    }

    @Override
    public @Nullable
    CallAdapter<?, ?> get(
            Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != MyCall.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalStateException(
                    "MyCall must have generic type (e.g., MyCall<ResponseBody>)");
        }
        Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Executor callbackExecutor = retrofit.callbackExecutor();
        return new PocErrorHandlingCallAdapter<>(responseType, callbackExecutor, iGlobalListener);
    }

    private static final class PocErrorHandlingCallAdapter<R> implements CallAdapter<R, MyCall<R>> {
        private final Type responseType;
        private final Executor callbackExecutor;
        private IGlobalCallback iGlobalListener;

        PocErrorHandlingCallAdapter(Type responseType, Executor callbackExecutor, IGlobalCallback iGlobalListener) {
            this.responseType = responseType;
            this.callbackExecutor = callbackExecutor;
            this.iGlobalListener = iGlobalListener;
        }

        @Override
        public Type responseType() {
            return responseType;
        }

        @Override
        public MyCall<R> adapt(Call<R> call) {
            return new PocMyCallAdapter<>(call, callbackExecutor, iGlobalListener);
        }
    }
}