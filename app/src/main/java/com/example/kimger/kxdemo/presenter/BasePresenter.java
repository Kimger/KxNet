package com.example.kimger.kxdemo.presenter;

import android.content.Context;
import android.view.View;

import com.example.kimger.kxdemo.iview.IBaseView;
import com.example.kimger.kxdemo.model.IBaseModel;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/24 0024 10:44
 * @description
 */
public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> {

    private V mProxyView;
    private M module;
    private WeakReference<V> mWeakReference;

    /**
     * 绑定View
     *
     * @param view
     */
    @SuppressWarnings("unchecked")
    public void attachView(V view) {
        mWeakReference = new WeakReference<>(view);
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new
                MvpViewHandler(mWeakReference.get()));
        if (this.module == null) {
            this.module = createModule();
        }
    }

    public void detachView() {
        this.module = null;
        if (isViewAttached()) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

    protected boolean isViewAttached() {
        return mWeakReference != null && mWeakReference.get() != null;
    }

    protected V getView() {
        return mProxyView;
    }

    protected M getModule() {
        return module;
    }

    protected Context getContext() {
        return getView().getContext();
    }

    protected void showLoading() {
        getView().showLoading();
    }

    protected void dismissLoading() {
        getView().dismissLoading();
    }

    /**
     * 创建Module
     *
     * @return
     */
    protected abstract M createModule();

    public abstract void start();

    private class MvpViewHandler implements InvocationHandler {
        private IBaseView mvpView;

        MvpViewHandler(IBaseView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException,
                IllegalAccessException {
            //如果V层没被销毁，执行V层方法
            if (isViewAttached()) {
                return method.invoke(mvpView, args);
            }//P层不需要关注V层返回值
            return null;
        }
    }

}
