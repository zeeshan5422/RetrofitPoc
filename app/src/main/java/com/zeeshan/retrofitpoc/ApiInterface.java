package com.zeeshan.retrofitpoc;

import com.zeeshan.retrofitpoc.errorhandling.MyCall;
import com.zeeshan.retrofitpoc.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("todos")
    Call<List<User>> getGenericUsers();

//    @GET("todos")
//    MyCall<List<User>> getUsers();
////
//    @GET("todos")
//    Call<List<User>> getUserz();
//
//    @POST("addUser")
//    Call<User> putUsers(@Body User user);
}
