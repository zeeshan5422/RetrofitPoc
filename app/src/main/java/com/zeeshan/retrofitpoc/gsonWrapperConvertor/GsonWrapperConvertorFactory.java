package com.zeeshan.retrofitpoc.gsonWrapperConvertor;

import androidx.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GsonWrapperConvertorFactory extends Converter.Factory {

    private GsonConverterFactory factory;

    public GsonWrapperConvertorFactory(GsonConverterFactory factory) {
        this.factory = factory;
    }

    public GsonWrapperConvertorFactory() {
        super();
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type,
                                                            Annotation[] annotations, Retrofit retrofit) {
        // e.g. WrapperResponse<UserProfile>
        Type wrappedType = type;
        wrappedType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{type};
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type getRawType() {
                return WrapperResponse.class;
            }
        };


        Converter<ResponseBody, ?> gsonConverter = factory
                .responseBodyConverter(wrappedType, annotations, retrofit);
        return new WrapperResponseBodyConverter(gsonConverter);
    }


//    @Nullable
//    @Override
//    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
//        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
//    }
//
//    @Nullable
//    @Override
//    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//        return super.stringConverter(type, annotations, retrofit);
//    }
}
