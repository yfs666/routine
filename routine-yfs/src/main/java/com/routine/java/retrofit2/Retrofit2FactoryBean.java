package com.routine.java.retrofit2;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import lombok.Setter;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;


public class Retrofit2FactoryBean<T> implements FactoryBean<T>, EmbeddedValueResolverAware {

    @Setter
    private Class<?> retrofit2Interface;

    private StringValueResolver stringValueResolver;

    @Override
    public T getObject() throws Exception {
        Retrofit2 annotation = this.retrofit2Interface.getAnnotation(Retrofit2.class);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.stringValueResolver.resolveStringValue(annotation.domainUrl()))
                .client(initOkHttpClient())
                .addConverterFactory(Retrofit2ConverterFactory.create())
                .build();
        return (T) retrofit.create(this.retrofit2Interface);
    }

    @Override
    public Class<?> getObjectType() {
        return this.retrofit2Interface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }

    private OkHttpClient initOkHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .eventListenerFactory(CatEventListener.LISTENER_FACTORY)
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS)
                .build();
    }
}
