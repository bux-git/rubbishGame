package com.zx.rubbishgame.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.zx.rubbishgame.StartGameActivity;

public class CountTimer extends CountDownTimer {
    private static final String TAG = "CountTimer";
    private Context context;
    private TextView mTextView;

    /**
     * 参数 millisInFuture       倒计时总时间（如60S，120s等）
     * 参数 countDownInterval    渐变时间（每次倒计1s）
     */
    public CountTimer(long millisInFuture, long countDownInterval, Context context) {
        super(millisInFuture, countDownInterval);
        this.context = context;
    }

    public CountTimer(Context context, Window window) {
        super(DataCons.MAX_GO_MAIN, 1000);
        this.context = context;
    }

    public CountTimer() {
        super(DataCons.MAX_GO_MAIN, 1000);
    }

    public void setContext(Context context){
        this.context=context;
    }


    // 计时完毕时触发
    @Override
    public void onFinish() {
        if (!(context instanceof StartGameActivity)) {
            StartGameActivity.start(context);
        }
    }

    // 计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {
        Log.d(TAG, "onTick: ");
    }

    /**
     * 重新开始
     */
    public void reset() {
        cancel();
        start();
    }

    /**
     * 关闭
     */
    public void stop() {
        cancel();
    }
}