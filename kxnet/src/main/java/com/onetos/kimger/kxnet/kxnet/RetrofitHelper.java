package com.onetos.kimger.kxnet.kxnet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024 16:52
 * @description
 */
public class RetrofitHelper {

    private long DEFAULT_TIME_OUT = 2000;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private static String sUrl;

    private static class SingletonHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 初始化必要对象和参数
     *
     * @param url 基础baseUrl
     * @return
     */

    Retrofit getRetrofit(String url) {
        return getRetrofit(url, null, null);
    }

    private Retrofit getRetrofit(String url, String headerKey, String headerValue) {
        // 初始化okhttp
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient().newBuilder()
                    .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            okhttp3.Response originalResponse = chain.proceed(request);
                            return originalResponse.newBuilder().header("key1", "value1").build();
                        }
                    })
                    .retryOnConnectionFailure(true)
                    .build();
        }
        // 初始化Retrofit
        if (mRetrofit != null && url.equals(sUrl)) {
            return mRetrofit;
        }
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        sUrl = url;
        return mRetrofit;
    }

    void addHead() {

    }

//    //返回一个泛型类
//    public static <T> T getService(Class<T> service) {
//        return getInstance().getRetrofit(ApiService.BASE_URL).create(service);
//    }
//
//    public static ApiService getApiService() {
//        return getInstance().getRetrofit(ApiService.BASE_URL).create(ApiService.class);
//    }
}
