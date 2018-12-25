package com.onetos.kimger.kxnet.kxnet;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024 16:31
 * @description
 */
public class ApiManager {


    private static class SingletonHolder {
        private static final ApiManager INSTANCE = new ApiManager();
    }


    public static ApiManager getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public <T> T getApiService(Class<T> service) {
        return KxNet.getRetrofit().create(service);
    }


}
