package com.zeeshan.retrofitpoc.errorhandling;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class PocMyCallAdapter<T> implements MyCall<T> {
    private final Call<T> call;
    private final Executor callbackExecutor;
    private final IGlobalCallback iGlobalListener;

    PocMyCallAdapter(Call<T> call, Executor callbackExecutor, IGlobalCallback iGlobalListener) {
        this.call = call;
        this.callbackExecutor = callbackExecutor;
        this.iGlobalListener = iGlobalListener;
    }

    @Override
    public void cancel() {
        call.cancel();
    }

    @Override
    public void enqueue(final MyCallback<T> callback) {

        if (!NetworkUtils.isConnected() && iGlobalListener != null) {
            iGlobalListener.onInternetConnectionError();
            return;
        }

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, final Response<T> response) {
                // TODO if 'callbackExecutor' is not null, the 'callback' methods should be executed
                // on that executor by submitting a Runnable. This is left as an exercise for the reader.

                callbackExecutor.execute(new Runnable() {
                    @Override
                    public void run() {

                        int code = response.code();
                        if (code >= 200 && code < 300) {
                            if (iGlobalListener != null) {
                                iGlobalListener.onSuccess();
                            }
                            callback.success(response);
                        } else if (code == 401) {
                            if (iGlobalListener != null) {
                                iGlobalListener.onUnAuthenticated();
                            }
                            callback.unauthenticated(response);
                        } else if (code >= 400 && code < 500) {
                            callback.clientError(response);
                        } else if (code >= 500 && code < 600) {
                            callback.serverError(response);
                        } else {
                            callback.unexpectedError(new RuntimeException("Unexpected response " + response));
                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                // TODO if 'callbackExecutor' is not null, the 'callback' methods should be executed
                // on that executor by submitting a Runnable. This is left as an exercise for the reader.

                if (t instanceof IOException) {
                    callback.networkError((IOException) t);
                } else {
                    callback.unexpectedError(t);
                }
            }
        });
    }

    @Override
    public MyCall<T> clone() {
        return new PocMyCallAdapter<>(call.clone(), callbackExecutor, iGlobalListener);
    }
}
