package com.zeeshan.retrofitpoc.genericApiReqHandler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Response;

public abstract class GenericRequestHandler<R> {

    abstract protected Call<R> makeRequest();

    public final LiveData<DataWrapper<R>> doRequest() {
        final MutableLiveData<DataWrapper<R>> liveData = new MutableLiveData<>();
        final DataWrapper<R> dataWrapper = new DataWrapper<R>();
        makeRequest().enqueue(new ZApiCallback<R>() {

            @Override
            protected void handleSuccess(R data) {
                dataWrapper.setData(data);
                liveData.setValue(dataWrapper);
            }

            @Override
            protected void handleError(Response<R> response) {
                dataWrapper.setApiException(ApiErrorHandler.getErrorData(response));
                liveData.setValue(dataWrapper);
            }

            @Override
            protected void handleException(Exception t) {
                dataWrapper.setApiException(t);
                liveData.setValue(dataWrapper);
            }
        });
        return liveData;
    }

    public final LiveData<DataWrapper<R>> doRequestWithCaller(Call<R> caller) {
        final MutableLiveData<DataWrapper<R>> liveData = new MutableLiveData<>();
        final DataWrapper<R> dataWrapper = new DataWrapper<R>();
        caller.enqueue(new ZApiCallback<R>() {

            @Override
            protected void handleSuccess(R data) {
                dataWrapper.setData(data);
                liveData.setValue(dataWrapper);
            }

            @Override
            protected void handleError(Response<R> response) {
                dataWrapper.setApiException(ApiErrorHandler.getErrorData(response));
                liveData.setValue(dataWrapper);
            }

            @Override
            protected void handleException(Exception t) {
                dataWrapper.setApiException(t);
                liveData.setValue(dataWrapper);
            }
        });
        return liveData;
    }

    public final LiveData<R> doRequestWithCaller(Type responseType, Call<R> caller) {
        final MutableLiveData<R> liveData = new MutableLiveData<>();
//        final DataWrapper<R> dataWrapper = new DataWrapper<R>();
        caller.enqueue(new ZApiCallback<R>() {

            @Override
            protected void handleSuccess(R data) {
                liveData.setValue(data);
            }

            @Override
            protected void handleError(Response<R> response) {
                liveData.setValue(response.body());
            }

            @Override
            protected void handleException(Exception t) {
//                liveData.setValue(dataWrapper.);
            }
        });
        return liveData;
    }
}
