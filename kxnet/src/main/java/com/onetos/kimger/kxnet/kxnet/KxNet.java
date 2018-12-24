package com.onetos.kimger.kxnet.kxnet;

import android.support.annotation.Nullable;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024 11:38
 * @description
 */
public class KxNet {

    private final String baseUrl;
    private final Converter.Factory converterFactory;
    private final CallAdapter.Factory callAdapterFactory;
    private Retrofit mRetrofit;
    private final static String BASE_URL = ApiService.BASE_URL;


    public KxNet(String baseUrl, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory) {
        this.baseUrl = baseUrl;
        this.converterFactory = converterFactory;
        this.callAdapterFactory = callAdapterFactory;
        mRetrofit = createRetrofit();
    }

    public static KxNet getDefault() {
        return new KxNet.Builder().build();
    }

    public static KxNet newInstance(KxNet.Builder builder) {
        return builder.build();
    }

    public static Builder newInstance() {
        return new Builder();
    }

    public Retrofit createRetrofit() {
        return RetrofitHelper.getInstance().getRetrofit(baseUrl);
    }

    public <T> void exec(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 支持背压
     *
     * @param flowable
     * @param subscriber
     * @param <T>
     */
    public <T> void exec(Flowable<T> flowable, Subscriber<T> subscriber) {
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public static final class Builder {

        private String baseUrl;
        private Converter.Factory converterFactory;
        private CallAdapter.Factory factory;
        private Flowable flowable;

        public Builder baseUrl(String baseUrl) {
            checkNotNull(baseUrl, "baseUrl == null");
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder addConverterFactory(Converter.Factory converterFactory) {
            checkNotNull(converterFactory, "callAdapterFactory == null");
            this.converterFactory = converterFactory;
            return this;
        }

        public Builder addCallAdapterFactory(CallAdapter.Factory factory) {
            checkNotNull(factory, "callAdapterFactory == null");
            this.factory = factory;
            return this;
        }

        public KxNet build() {
            return new KxNet(baseUrl != null ? baseUrl : BASE_URL, converterFactory != null ? converterFactory :
                    GsonConverterFactory.create(), factory !=
                    null ? factory : RxJava2CallAdapterFactory.create());
        }
    }


    static <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
