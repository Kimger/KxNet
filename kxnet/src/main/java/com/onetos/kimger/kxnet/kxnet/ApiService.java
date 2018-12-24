package com.onetos.kimger.kxnet.kxnet;

import java.util.logging.LoggingMXBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024 14:55
 * @description
 */
public interface ApiService {

    String BASE_URL = "https://api.douban.com/v2/movie/";

    @GET("top250")
    Flowable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

}
