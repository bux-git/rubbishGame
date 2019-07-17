package com.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.billy.android.loading.Gloading;
import com.common.widget.gload.view.GLoadData;
import com.hwangjr.rxbus.RxBus;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @description：
 * @author：bux on 2018/4/3 11:11
 * @email: 471025316@qq.com
 */

public abstract class BaseFragment extends RxFragment {
    private static final String TAG = "BaseFragment";

    Unbinder mUnbinder;
    protected View mRootView;
    protected Gloading.Holder mHolder;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Log.d(TAG, "onCreateView: ");
        RxBus.get().register(this);
        mRootView = inflater.inflate(setLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        //initLoadingStatusViewIfNeed();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    protected abstract int setLayoutId();


    protected abstract void init();

    @Override
    public void onResume() {
        super.onResume();
        //Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
       // Log.d(TAG, "onPause: ");
    }

    @Override
    public void onDestroyView() {
        // Log.d(TAG, "onDestroyView: ");

        RxBus.get().unregister(this);
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
         //Log.d(TAG, "onDestroy: ");
        super.onDestroy();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**全局空view设置
     * make a Gloading.Holder wrap with current activity by default
     * override this method in subclass to do special initialization
     *
     */
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null
        &&getWrapView()!=null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(getWrapView()).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    /**
     * 共有空值View
     * @return
     */
    protected  View getWrapView(){
        return null;
    }

    protected void onLoadRetry() {
        // override this method in subclass to do retry task
    }

    public void showLoading() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoading();
    }

    public void showLoadSuccess() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadSuccess();
    }

    public void showLoadFailed() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadFailed();
    }

    public void showEmpty() {
        initLoadingStatusViewIfNeed();
        mHolder.showEmpty();
    }

    public void showEmpty(int emptyMsg,int emptyImg) {
        initLoadingStatusViewIfNeed();
        mHolder.withData(new GLoadData(emptyMsg,emptyImg)).showEmpty();
    }


}
