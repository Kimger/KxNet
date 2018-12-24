package com.example.kimger.kxdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.onetos.kimger.kxnet.kxnet.ApiManager;
import com.onetos.kimger.kxnet.kxnet.KxNet;
import com.onetos.kimger.kxnet.kxnet.MovieEntity;
import com.onetos.kimger.kxnet.kxnet.MySubScriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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


                KxNet.getDefault().exec(ApiManager.getDefaultApi().getTopMovie(0, 20), new MySubScriber<MovieEntity>() {
                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onSuccess(MovieEntity movieEntity) {
                        Log.d(TAG, "onSuccess: ");
                        Toast.makeText(MainActivity.this, movieEntity.getSubjects().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

}
