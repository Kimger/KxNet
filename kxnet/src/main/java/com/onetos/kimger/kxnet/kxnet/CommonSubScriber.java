package com.onetos.kimger.kxnet.kxnet;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024 17:13
 * @description
 */
public abstract class CommonSubScriber<T> extends ResourceSubscriber<T> {

    @Override
    public void onNext(T t) {

        onSuccess(t);
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    @Override
    public void onError(Throwable t) {
        onError(t.getMessage());
    }

    public abstract void onSuccess(T t);

    public abstract void onError(String msg);

    public abstract void onFinish();

}
