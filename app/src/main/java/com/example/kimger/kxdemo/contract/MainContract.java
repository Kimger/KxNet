package com.example.kimger.kxdemo.contract;

import com.example.kimger.kxdemo.iview.IBaseView;
import com.example.kimger.kxdemo.model.IBaseModel;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024 17:21
 * @description
 */
public interface MainContract {
    interface View extends IBaseView{

    }
    interface Model extends IBaseModel{
        void getData();
    }
    interface Presenter{
        void get();
    }
}
