package com.onetos.kimger.kxnet.kxnet;

import android.content.Context;

import io.reactivex.Flowable;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.onetos.kimger.kxnet.kxnet.KxUtil.checkNotNull;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/25 0025 16:03
 * @description
 */
public class KxBuilder {
    private Context context;
    private String baseUrl;
    private Converter.Factory converterFactory;
    private CallAdapter.Factory callAdapterFactory;
    private Retrofit mRetrofit;

    public String getBaseUrl() {
        if (baseUrl == null) {
            KxUtil.throwValidation("KxNet baseUrl is null!!! This must not be empty ");
        }
        return baseUrl;
    }

    public Converter.Factory getConverterFactory() {
        if (converterFactory == null) {
            converterFactory = GsonConverterFactory.create();
        }
        return converterFactory;
    }

    public CallAdapter.Factory getCallAdapterFactory() {
        if (callAdapterFactory == null) {
            callAdapterFactory = RxJava2CallAdapterFactory.create();
        }
        return callAdapterFactory;
    }

    public Retrofit getRetrofit(String... url) {
        String tUrl = null;
        if (url != null && url.length > 0) {
            tUrl = url[0];
        }
        if (mRetrofit == null) {
            mRetrofit = RetrofitHelper.getInstance().getRetrofit(tUrl != null ? tUrl : baseUrl);
        }
        return mRetrofit;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        checkNotNull(context, "context == null");
        this.context = context;
    }

    public KxBuilder retrofitClient(Retrofit retrofit) {
        checkNotNull(retrofit, "retrofit == null");
        this.mRetrofit = retrofit;
        return this;
    }

    public KxBuilder baseUrl(String baseUrl) {
        checkNotNull(baseUrl, "baseUrl == null");
        this.baseUrl = baseUrl;
        return this;
    }

    public KxBuilder addConverterFactory(Converter.Factory converterFactory) {
        checkNotNull(converterFactory, "callAdapterFactory == null");
        this.converterFactory = converterFactory;
        return this;
    }

    public KxBuilder addCallAdapterFactory(CallAdapter.Factory factory) {
        checkNotNull(factory, "callAdapterFactory == null");
        this.callAdapterFactory = factory;
        return this;
    }

    public void build() {
        KxNet.build(this);
    }
}
