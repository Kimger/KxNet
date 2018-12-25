package com.example.kimger.kxdemo;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024 14:55
 * @description
 */
public interface ApiService {

    String BASE_URL = "https://api.douban.com/v2/movie/";
    String BASE_URL2 = "https://api.douban.com/v2/movie/22222222222/";

    @GET("top250")
    Flowable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

}
