package com.zx.rubbishgame;

import android.app.Application;
import android.content.Context;

import com.common.AppFrontBackHelper;
import com.common.di.component.AppComponent;
import com.common.di.component.DaggerAppComponent;
import com.common.di.module.AppModule;
import com.common.di.module.HttpModule;
import com.google.gson.Gson;
import com.kk.taurus.ijkplayer.IjkPlayer;
import com.kk.taurus.playerbase.config.PlayerConfig;
import com.kk.taurus.playerbase.config.PlayerLibrary;
import com.kk.taurus.playerbase.log.PLog;
import com.kk.taurus.playerbase.record.PlayRecordManager;
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

    /**
     * 视频缓存
     *
     * @param url
     * @return
     */
    public static String getProxyUrl(String url) {
        return mMyApplication.getAppComponent().getProxyCacheServer().getProxyUrl(url);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
        initActivityLifecycle();
        initPlay();
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

    private void initPlay() {
        PLog.LOG_OPEN = BuildConfig.DEBUG;

        //如果您想使用默认的网络状态事件生产者，请添加此行配置。
        //并需要添加权限 android.permission.ACCESS_NETWORK_STATE
        PlayerConfig.setUseDefaultNetworkEventProducer(true);

        //初始化解码器
        IjkPlayer.init(this);
        //播放记录的配置
        //开启播放记录
        PlayerConfig.playRecord(true);
        PlayRecordManager.setRecordConfig(
                new PlayRecordManager.RecordConfig.Builder()
                        .setMaxRecordCount(10).build());
        //初始化库
        PlayerLibrary.init(this);
    }





}
