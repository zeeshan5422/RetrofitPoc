package com.zeeshan.retrofitpoc.customCallAdapter;

import androidx.annotation.NonNull;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

public class SimpleCallAdapter<T> implements CallAdapter<T, Object> {

    private Type responseType;

    public SimpleCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public Object adapt(@NonNull Call<T> call) {
        return new Simple<>(call);
    }
}
