package com.onetos.kimger.kxnet.kxnet;

import android.content.Context;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/25 0025 16:07
 * @description
 */
public class DefaultKxProcessor<T> implements KxProcessor<T> {

    private Context context;
    private String baseUrl;
    private Converter.Factory converterFactory;
    private CallAdapter.Factory callAdapterFactory;
    private Retrofit mRetrofit;

    public DefaultKxProcessor(KxBuilder kxBuilder) {
        this.context = kxBuilder.getContext();
        this.baseUrl = kxBuilder.getBaseUrl();
        this.converterFactory = kxBuilder.getConverterFactory();
        this.callAdapterFactory = kxBuilder.getCallAdapterFactory();
        this.mRetrofit = kxBuilder.getRetrofit();
    }

    @Override
    public void exec(Flowable<T> flowable, Subscriber<T> subscriber) {
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onBackpressureDrop()
                .subscribe(subscriber);
    }

    @Override
    public void exec(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public KxNet getDefault() {

        return null;
    }

    @Override
    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    @Override
    public void changeUrl(String url) {
        if (url == null) {
            KxUtil.throwValidation("KxNet,url must not be null!!");
        }
        String lastStr = url.substring(url.length() - 1);
        if (!"/".equals(lastStr)) {
            KxUtil.throwValidation("KxNet,url must end in '/' ," + "not be '" + lastStr + "' " + url);
        }
        mRetrofit = RetrofitHelper.getInstance().getRetrofit(url);
    }


}
