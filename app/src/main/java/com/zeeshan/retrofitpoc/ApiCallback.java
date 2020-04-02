package com.zeeshan.retrofitpoc;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    public abstract void onCallResponse(Call<T> call, Response<T> response);
    public abstract void onCallFailure(Call<T> call, Throwable t);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onCallResponse(call, response);
            return;
        }
        Log.v("MainActivity", "parent class response callback" + response.toString());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.v("MainActivity", "parent class response callback" + t.getMessage());
        onCallFailure(call,t);

    }
}
