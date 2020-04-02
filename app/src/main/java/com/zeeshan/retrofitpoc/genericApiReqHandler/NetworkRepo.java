package com.zeeshan.retrofitpoc.genericApiReqHandler;

import androidx.lifecycle.LiveData;

import com.zeeshan.retrofitpoc.ApiInterface;

import retrofit2.Call;

public class NetworkRepo<T> extends GenericRequestHandler<T> {

    private ApiInterface apiInterface;

    private Call<T> callingAPi;

    NetworkRepo() {
        apiInterface = GenericApiManager.getApi().create(ApiInterface.class);
    }

    @Override
    protected Call<T> makeRequest() {
        return null;
    }

    public LiveData<DataWrapper<T>> provideUsers() {
        callingAPi = (Call<T>) apiInterface.getGenericUsers();
        return doRequestWithCaller(callingAPi);
    }
}
