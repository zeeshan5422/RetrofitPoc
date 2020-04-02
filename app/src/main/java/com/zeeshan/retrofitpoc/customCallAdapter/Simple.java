package com.zeeshan.retrofitpoc.customCallAdapter;

import retrofit2.Call;

public class Simple<T> {

    public Simple(Call<T> call) {
    }

    // this will handle synchronous call
    public void run() {

    }

    // this will handle asynchronous call
    public void process() {
    }

    // this will handle the response
    public void handleResponse() {
    }
}
