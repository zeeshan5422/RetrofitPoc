package com.zeeshan.retrofitpoc.genericApiReqHandler;

import retrofit2.Response;

public class ApiErrorHandler {

    public static <R> Exception getErrorData(Response<R> response) {
        return null;
    }
}
