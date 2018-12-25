package com.onetos.kimger.kxnet.kxnet;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Retrofit;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/25 0025 16:07
 * @description
 */
interface KxProcessor<T> {

    void exec(Flowable<T> flowable, Subscriber<T> subscriber);

    void exec(Observable<T> observable, Observer<T> observer);

    KxNet getDefault();

    Retrofit getRetrofit();

    void changeUrl(String url);

    class EmptyKxProcessor implements KxProcessor {


        @Override
        public void exec(Flowable flowable, Subscriber subscriber) {
            KxUtil.throwValidation();
        }

        @Override
        public void exec(Observable observable, Observer observer) {
            KxUtil.throwValidation();
        }

        @Override
        public KxNet getDefault() {
            KxUtil.throwValidation();
            return null;
        }

        @Override
        public Retrofit getRetrofit() {
            KxUtil.throwValidation();
            return null;
        }

        @Override
        public void changeUrl(String url) {
            KxUtil.throwValidation();
        }

    }


}
