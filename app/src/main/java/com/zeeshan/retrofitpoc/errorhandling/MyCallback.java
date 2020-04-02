package com.zeeshan.retrofitpoc.errorhandling;

import java.io.IOException;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * A callback which offers granular callbacks for various conditions.
 */
public interface MyCallback<T>{
    /**
     * Called for [200, 300) responses.
     */
    void success(Response<T> response);

    /**
     * Called for 401 responses.
     */
    void unauthenticated(Response<?> response);

    /**
     * Called for [400, 500) responses, except 401.
     */
    void clientError(Response<?> response);

    /**
     * Called for [500, 600) response.
     */
    void serverError(Response<?> response);

    /**
     * Called for network errors while making the call.
     */
    void networkError(IOException e);

    /**
     * Called for unexpected errors while making the call.
     */
    void unexpectedError(Throwable t);
}