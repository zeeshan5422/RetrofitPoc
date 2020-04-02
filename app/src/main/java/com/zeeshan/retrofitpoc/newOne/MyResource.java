package com.zeeshan.retrofitpoc.newOne;

import androidx.annotation.Nullable;

public interface MyResource<T> {
    /**
     * indicate the resource loading
     *
     * @param isLoading true if loading false otherwise
     */
    void onLoading(boolean isLoading);

    /**
     * called when an exception is thrown by the resource
     *
     * @param throwable
     */
    void onError(Throwable throwable);

    /**
     * @param t resource response , it could be null.
     */
    void onSuccess(@Nullable T t);

}
