package com.example.kimger.kxdemo;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/25 0025 18:30
 * @description
 */
public class Api {
    public <T> T get(String url, Map<String, T> maps, BaseSubscriber<ResponseBody> subscriber) {
        return (T) ApiService.get(url, maps)
                .compose(schedulersTransformer)
                .compose(handleErrTransformer())
                .subscribe(subscriber);
    }
}
