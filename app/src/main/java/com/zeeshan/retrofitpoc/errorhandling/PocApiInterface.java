package com.zeeshan.retrofitpoc.errorhandling;

import com.zeeshan.retrofitpoc.models.User;

import java.util.List;

import retrofit2.http.GET;

public interface PocApiInterface {

    @GET("todos")
    MyCall<List<User>> getUsers();
}
