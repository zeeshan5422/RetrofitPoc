package com.zeeshan.retrofitpoc;

import com.zeeshan.retrofitpoc.errorhandling.MyCall;
import com.zeeshan.retrofitpoc.models.User;

import java.util.List;

import retrofit2.Call;

public class ApiInterfaceCallbacks implements ApiInterface {

    @Override
    public Call<List<User>> getGenericUsers() {
        return null;
    }

//    @Override
//    public MyCall<List<User>> getUsers() {
//        throw new RuntimeException("#getUsers called, but #getUsers has not implemented.");
//    }
//
//    @Override
//    public Call<List<User>> getUserz() {
//        throw new RuntimeException("#getUsers called, but #getUsers has not implemented.");
//    }
//
//    @Override
//    public Call<User> putUsers(User user) {
//        throw new RuntimeException("#putUsers called, but #putUsers has not implemented.");
//    }
}
