package com.example.kimger.kxdemo;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.os.Bundle;

import com.onetos.kimger.kxnet.kxnet.ActivityLifeCycleEvent;
import com.onetos.kimger.kxnet.kxnet.ApiManager;
import com.onetos.kimger.kxnet.kxnet.KxNet;
import com.orhanobut.hawk.Hawk;

import io.reactivex.subjects.PublishSubject;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/25 0025 10:48
 * @description
 */
public class KxApplication extends Application {
    private final PublishSubject<ActivityLifeCycleEvent> mLifeCycleSubject = PublishSubject.create();

    @Override
    public void onCreate() {
        super.onCreate();

        KxNet.init(this).baseUrl(ApiService.BASE_URL).build();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                mLifeCycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                mLifeCycleSubject.onNext(ActivityLifeCycleEvent.START);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                mLifeCycleSubject.onNext(ActivityLifeCycleEvent.RESUME);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                mLifeCycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                mLifeCycleSubject.onNext(ActivityLifeCycleEvent.STOP);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                mLifeCycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
            }
        });
    }
}
