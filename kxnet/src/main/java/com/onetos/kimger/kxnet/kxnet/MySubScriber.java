package com.onetos.kimger.kxnet.kxnet;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024 17:13
 * @description
 */
public abstract class MySubScriber<T> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(1);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }

    @SuppressWarnings("unchecked")
    public abstract void onError();

    @SuppressWarnings("unchecked")
    public abstract void onSuccess(T t);

}
