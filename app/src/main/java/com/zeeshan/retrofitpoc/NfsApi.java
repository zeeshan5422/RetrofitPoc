package com.zeeshan.retrofitpoc;

public class NfsApi<T> {

    private static final NfsApi mInstance = _buildInstance();

    public static NfsApi getInstance() {
        return mInstance;
    }

    private static NfsApi _buildInstance() {
        return new NfsApi();
    }

    private T apiInterface;

    public NfsApi() {
//        apiInterface = Api.getApi().create(ApiInterface.class);
    }

    public T getApi() {
        return apiInterface;
    }

    public void execute() {

    }


    public static class Builder<T> {

        private ApiCallback mCallback = null;
        private T apiInterface;

        public Builder() {
        }

        public Builder api() {
            return this;
        }

        public Builder onResponse(ApiCallback callback) {
            this.mCallback = callback;
            return this;
        }

        public void enqueue() {
            //TODO
        }

        public void execute() {
            //TODO
        }

    }
}
