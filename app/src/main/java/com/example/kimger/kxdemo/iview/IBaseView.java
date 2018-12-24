package com.example.kimger.kxdemo.iview;

import android.content.Context;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024 10:43
 * @description
 */
public interface IBaseView {

    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void dismissLoading();

    /**
     * 空数据
     *
     * @param tag TAG
     */
    void onEmpty(Object tag);

    /**
     * 错误数据
     *
     * @param tag      TAG
     * @param errorMsg 错误信息
     */
    void onError(Object tag, String errorMsg);

    /**
     * 上下文
     *
     * @return context
     */
    Context getContext();

}
