package com.zeeshan.retrofitpoc.genericApiReqHandler;

import androidx.lifecycle.LiveData;

import com.zeeshan.retrofitpoc.ApiInterface;
import com.zeeshan.retrofitpoc.models.User;

import java.util.List;

import retrofit2.Call;

public class UsersListApi extends GenericRequestHandler<List<User>> {

    private ApiInterface apiInterface;

    UsersListApi() {
        apiInterface = GenericApiManager.getApi().create(ApiInterface.class);
    }

    public LiveData<DataWrapper<List<User>>> getUsersList() {
        return doRequestWithCaller(apiInterface.getGenericUsers());
    }


    @Override
    protected Call<List<User>> makeRequest() {
        return apiInterface.getGenericUsers();
    }
}
