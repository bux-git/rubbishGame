package com.zx.rubbishgame;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.common.util.PermissionUtils;

import io.reactivex.functions.Consumer;

/**
 * @description：
 * @author：bux on 2019/4/10 9:31
 * @email: 471025316@qq.com
 */
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGlobalData();
        PermissionUtils.checkCameraVideo(this, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    StartGameActivity.start(SplashActivity.this);
                } else {
                    finish();
                }
            }
        });

        //延迟销毁
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }

    private void initGlobalData() {

    }


}
