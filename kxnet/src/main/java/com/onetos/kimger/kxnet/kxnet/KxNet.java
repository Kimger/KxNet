package com.onetos.kimger.kxnet.kxnet;

import android.content.Context;
import android.support.annotation.Nullable;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

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

    private static KxProcessor sProcessor = new KxProcessor.EmptyKxProcessor();
    private static KxBuilder sKxBuilder;


    public static KxBuilder init(Context context) {
        Hawk.init(context).build();
        sKxBuilder = new KxBuilder();
        return sKxBuilder;
    }


    public static void changeUrl(String url) {
        sProcessor.changeUrl(url);
    }

    @SuppressWarnings("unchecked")
    public static <T> void exec(Observable<T> observable, Observer<T> observer) {
        sProcessor.exec(observable, observer);
    }

    /**
     * 支持背压
     *
     * @param flowable
     * @param subscriber
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public static <T> void exec(Flowable<T> flowable, Subscriber<T> subscriber) {
        sProcessor.exec(flowable, subscriber);
    }

    public static Retrofit getRetrofit() {
        return sProcessor.getRetrofit();
    }

    public static void build(KxBuilder kxBuilder) {
        sProcessor = new DefaultKxProcessor(kxBuilder);
    }

    static class Holder {
        static KxNet INSTANCE = new KxNet();
    }

}
