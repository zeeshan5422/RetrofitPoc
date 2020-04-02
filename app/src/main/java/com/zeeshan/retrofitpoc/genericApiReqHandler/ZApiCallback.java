package com.zeeshan.retrofitpoc.genericApiReqHandler;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ZApiCallback<T> implements Callback<T> {

    abstract protected void handleSuccess(T data);

    abstract protected void handleError(Response<T> response);

    abstract protected void handleException(Exception t);

    @Override
    public void onResponse(@NonNull Call<T> call, Response<T> response) {
        if (response.body() != null) {
            handleSuccess((T) response.body());
        } else {
            handleError(response);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, Throwable t) {
        if (t instanceof Exception) {
            handleException((Exception) t);
        } else {
            //do something else
        }
    }
}
