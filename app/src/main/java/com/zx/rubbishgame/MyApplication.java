package com.zx.rubbishgame;

import android.app.Application;
import android.content.Context;

import com.common.AppFrontBackHelper;
import com.common.di.component.AppComponent;
import com.common.di.component.DaggerAppComponent;
import com.common.di.module.AppModule;
import com.common.di.module.HttpModule;
import com.google.gson.Gson;
import com.zx.rubbishgame.utils.CountTimer;

/**
 * description：
 * author：bux on 2019/7/17 9:33
 * email: 471025316@qq.com
 */
public class MyApplication extends Application {

    private static MyApplication mMyApplication;
    public static boolean ignoreMobile = true;
    public static boolean isNetWorkConnection = false;

    AppComponent mAppComponent;
    private CountTimer mCountTimer;


    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .httpModule(new HttpModule()).build();
        }
        return mAppComponent;
    }

    public static MyApplication get() {
        return mMyApplication;
    }

    public static Gson getGson() {
        return mMyApplication.getAppComponent().getGson();
    }


    public CountTimer getCountTime(Context context) {
        if (mCountTimer == null) {
            mCountTimer = new CountTimer();
        }
        mCountTimer.setContext(context);
        return mCountTimer;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
        initActivityLifecycle();
    }



    public void initActivityLifecycle() {
        AppFrontBackHelper helper = new AppFrontBackHelper();
        helper.register(MyApplication.get(), new AppFrontBackHelper.OnAppStatusListener() {
            @Override
            public void onFront() {



            }

            @Override
            public void onBack() {
                //应用切到后台处理
                // MTConnectUtil.close();
                //PrintUtil.closeConnect();

            }
        });

    }





}
