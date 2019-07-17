package com.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.common.util
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class DensityUtil {

    private static final String TAG = "DensityUtil";

    public static int dip2px(Context c, float dpValue) {
        final float scale = c.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context c, float pxValue) {
        final float scale = c.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int px2sp(Context c, float pxValue) {
        float fontScale = c.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    public static int sp2px(Context c, float spValue) {
        float fontScale = c.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int sp2dip(Context c, float spValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, c.getResources().getDisplayMetrics()));
    }

    public static int getScreenW(Context c) {
        return c.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenH(Context c) {
        return c.getResources().getDisplayMetrics().heightPixels;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getScreenRealH(Context context) {
        int h;
        WindowManager winMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = winMgr.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealMetrics(dm);
            h = dm.heightPixels;
        } else {
            try {
                Method method = Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class);
                method.invoke(display, dm);
                h = dm.heightPixels;
            } catch (Exception e) {
                display.getMetrics(dm);
                h = dm.heightPixels;
            }
        }
        return h;
    }

    public static int getStatusBarH(Context context) {
        Class<?> c;
        Object obj;
        Field field;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    public static int getNavigationBarrH(Context c) {
        Resources resources = c.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelOffset(identifier);
    }


    public static void getScreenSizeOfDevice(Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();

        double realWinch = point.x / dm.xdpi;
        double realHinch = point.y / dm.ydpi;
        double screenInches = Math.sqrt(Math.pow(realWinch, 2) + Math.pow(realHinch, 2));
        double realDpi=Math.sqrt(Math.pow(point.x, 2) +  Math.pow(point.y, 2))/screenInches;

        Log.d(TAG, "\r\n屏幕尺寸 : " + screenInches + "\r\n"
                + "宽尺寸:" + realWinch + "\r\n"
                + "高尺寸:" + realHinch + "\r\n"
                + "全屏分辨率:" + point + "\r\n"
                + "xDpi:" + dm.xdpi + "\r\n"
                + "yDpi:" + dm.ydpi + "\r\n"
                + "真实DPI:" + realDpi + "\r\n"
                + "--------------------------------" + "\r\n"
                + "分辨率:" + dm.widthPixels+"x"+dm.heightPixels + "\r\n"
                + "density:" + dm.densityDpi + "\r\n"
                + "宽DP:" +(dm.widthPixels*160)/dm.densityDpi + "\r\n"
                + "高DP:" + (dm.heightPixels*160)/dm.densityDpi + "\r\n");
    }

}
