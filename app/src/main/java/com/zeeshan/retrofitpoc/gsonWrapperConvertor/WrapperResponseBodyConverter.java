package com.zeeshan.retrofitpoc.gsonWrapperConvertor;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class WrapperResponseBodyConverter<T>
        implements Converter<ResponseBody, T> {
    private Converter<ResponseBody, WrapperResponse<T>> converter;

    public WrapperResponseBodyConverter(Converter<ResponseBody,
            WrapperResponse<T>> converter) {
        this.converter = converter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        WrapperResponse<T> response = converter.convert(value);
        if (!response.getError()) {
            return response.getData();
        }
        // todo:: handle error in your wrapperError class
        // RxJava will call onError with this exception
        throw new WrapperError(Long.valueOf(response.getStatus()), response.getMessage());
    }

}
