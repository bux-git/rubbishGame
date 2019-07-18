package com.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.billy.android.loading.Gloading;
import com.common.util.L;
import com.common.widget.RxToast;
import com.hwangjr.rxbus.RxBus;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zx.rubbishgame.MyApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @description：
 * @author：bux on 2018/4/3 10:51
 * @email: 471025316@qq.com
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    private static final String TAG = "BaseActivity";
    Unbinder mUnbinder;
    public Context mContext;
    protected Gloading.Holder mHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
        mContext = this;
        L.d("onCreate: " + this.getLocalClassName());
        setContentView(setLayoutId());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mUnbinder = ButterKnife.bind(this);


        init();
        initLoadingStatusViewIfNeed();
    }

    /**
     * 设置布局ID
     *
     * @return
     */
    protected abstract int setLayoutId();


    /**
     * 初始化操作
     */
    protected abstract void init();


    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }


    protected void showMsg(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.get().getCountTime(this).reset();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.get().getCountTime(this).stop();
    }

    /**
     * 主要的方法，重写dispatchTouchEvent
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            //获取触摸动作，如果ACTION_UP，计时开始。
            case MotionEvent.ACTION_UP:
                MyApplication.get().getCountTime(this).reset();
                break;
            //否则其他动作计时取消
            default:
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 设置状态栏
     */
    protected void initBarStatus() {

        /*if (!isNavBarImmersive()) {
            //设置bar颜色
            BarUtils.setStatusBarColor(this, ContextCompat.getColor(MyApplication.get(), R.color.colorPrimary), 0);
            //设置bar浅色模式
            BarUtils.setStatusBarLightMode(this, true);
            //顶部增加状态栏高度MarginTop
            if (isAddMarginTop()) {
                BarUtils.addMarginTopEqualStatusBarHeight(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0));
            }
        } else {
            BarUtils.setStatusBarAlpha(this, 0);
            //BarUtils.setNavBarImmersive(this);
        }*/
    }


    /**
     * 全局空view设置
     * make a Gloading.Holder wrap with current activity by default
     * override this method in subclass to do special initialization
     */
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(this).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
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


    /**
     * 沉浸式
     *
     * @return
     */
    protected boolean isNavBarImmersive() {
        return false;
    }


    protected boolean isAddMarginTop() {
        return true;
    }


}
