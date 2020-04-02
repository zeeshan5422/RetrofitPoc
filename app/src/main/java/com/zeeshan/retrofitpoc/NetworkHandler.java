package com.zeeshan.retrofitpoc;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class NetworkHandler {


    private ApiInterface apiInterface;

    private ApiCallback apiCallback;
    private Call apiCall;

    NetworkHandler() {
        //1- create retrofit instance,
        apiInterface = Api.getApi().create(ApiInterface.class);
    }

    public ApiInterface getApi() {
        return apiInterface;
    }


    public <T> void enqueue(Call<T> apiCall, ApiCallback mOnResponse) {
        apiCall.enqueue(mOnResponse);
    }

    public <T> Response<T> execute(Call<T> apiCall) throws IOException {
        return apiCall.execute();
    }

    public static class Builder {
        private ApiCallback apiCallback;
        private Call apiCall;

        Builder onResponse(ApiCallback apiCallback) {
            this.apiCallback = apiCallback;
            return this;
        }

        Builder addApi(Call apiCall) {
            this.apiCall = apiCall;
            return this;
        }

        void build(){
            if(apiCall == null){
                new IllegalArgumentException("ApiCall is null");
                return;
            }
        }
    }
}
