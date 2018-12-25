package com.example.kimger.kxdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onetos.kimger.kxnet.kxnet.ApiManager;
import com.onetos.kimger.kxnet.kxnet.KxNet;
import com.onetos.kimger.kxnet.kxnet.CommonSubScriber;

import java.util.HashMap;

/**
 * @param
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024
 * @return
 * @description
 */

public class MainActivity extends AppCompatActivity {

    String TAG = "xuejinge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn_get_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                KxNet.changeUrl(ApiService.BASE_URL2);
                ApiService apiService = ApiManager.getInstance().getApiService(ApiService.class);
                apiService.get(ApiService.BASE_URL2,new HashMap<String,String >());
                KxNet.exec(apiService.getTopMovie(0, 20), new CommonSubScriber<MovieEntity>() {
                    @Override
                    public void onSuccess(MovieEntity movieEntity) {

                    }

                    @Override
                    public void onError(String msg) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });

            }
        });

    }

}
