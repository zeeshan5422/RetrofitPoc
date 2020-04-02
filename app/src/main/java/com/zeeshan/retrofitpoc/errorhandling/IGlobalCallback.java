package com.zeeshan.retrofitpoc.errorhandling;

public interface IGlobalCallback {

    void onSuccess();

    void onUnAuthenticated();

    void onInternetConnectionError();

    void onDayEndError();
}
