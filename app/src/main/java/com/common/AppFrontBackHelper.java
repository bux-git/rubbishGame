package com.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zx.rubbishgame.R;


/**
 * APP前后台判断
 */
public class AppFrontBackHelper {
    private OnAppStatusListener mOnAppStatusListener;


    public AppFrontBackHelper() {

    }

    /**
     * 注册状态监听，仅在Application中使用
     *
     * @param application
     * @param listener
     */
    public void register(Application application, OnAppStatusListener listener) {
        mOnAppStatusListener = listener;
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public void unRegister(Application application) {
        application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        //打开的Activity数量统计
        private int activityStartCount = 0;

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(final Activity activity) {
            activityStartCount++;
            //数值从0变到1说明是从后台切到前台
            if (activityStartCount == 1) {
                //从后台切到前台
                if (mOnAppStatusListener != null) {
                    mOnAppStatusListener.onFront();
                }
            }

            // ActivityLifecycleCallbacks 中所有方法的调用时机都是在 Activity 对应生命周期的 Super 方法中进行的
            // 所以在 Activity 的 onCreate 方法中使用 setContentView 必须在 super.onCreate(savedInstanceState); 之前
            // 已经有多个title 多次判断

            // 找到 Toolbar 的标题栏并设置标题名
            View title = activity.findViewById(R.id.title);
            if (title != null) {
                String titleStr = (String) activity.getTitle();
                if (!titleStr.equals(activity.getString(R.string.app_name))) {
                    ((TextView) title).setText(titleStr);
                }
            }

            //找到 Toolbar 的返回按钮,并且设置点击事件,点击关闭这个 Activity
            View titleBack = activity.findViewById(R.id.title_back);
            if (titleBack != null) {
                if (!titleBack.hasOnClickListeners()) {
                    titleBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activity.finish();
                        }
                    });
                }
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            activityStartCount--;
            //数值从1到0说明是从前台切到后台
            if (activityStartCount == 0) {
                //从前台切到后台
                if (mOnAppStatusListener != null) {
                    mOnAppStatusListener.onBack();
                }
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

    public interface OnAppStatusListener {
        void onFront();

        void onBack();
    }
}