package com.zeeshan.retrofitpoc.newOne;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;

public class MyResponse<T> implements Callback<T> {
    private MyResource<T> res;

    private MyResponse(MyResource<T> resource) {
        res = resource;
    }

    public static <T> MyResponse<T> create(MyResource<T> resource) {
        if (resource == null)
            throw new NullPointerException("Resource interface can not be null");
        final MyResponse<T> response = new MyResponse<>(resource);
        resource.onLoading(true);
        return response;
    }

    public MyResponse<T> start() {
        res.onLoading(true);
        return this;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull retrofit2.Response<T> response) {
        res.onLoading(false);
        final T body = response.body();
        //add filtering here here , like http request status
        //if(response.code()==401){
        //res.onError(new UnauthorizedUserException());
        //    }

        res.onSuccess(body);

    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        res.onLoading(false);

        res.onError(t);
    }

}
