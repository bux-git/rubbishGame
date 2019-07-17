package com.common.util;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;
import com.common.widget.RxToast;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * @description：
 * @author：bux on 2018/6/8 15:29
 * @email: 471025316@qq.com
 */
public class CommonUtil {

    public static boolean isEmpty(TextView textView) {
        return TextUtils.isEmpty(textView.getText().toString().trim());
    }

    public static boolean isEmpty(String text) {
        return TextUtils.isEmpty(replaceNull(text));
    }


    public static String replaceNull(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (text.equals("null")) {
                return "";
            }
        } else {
            return "";
        }
        return text;
    }

    /**
     * map 参数拼接
     *
     * @param map map
     * @return 参数串
     */
    public static String paramsToGet(Map<String, String> map) {
        String split = "";
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append(split).append(key).append("=").append(map.get(key));
            split = "&";
        }
        return sb.toString().trim();
    }

    public static String paramsToPath(String... paths) {
        StringBuilder sb = new StringBuilder();
        String split = "";
        for (String key : paths) {
            sb.append(split).append(key);
            split = "/";
        }
        return sb.toString().trim();
    }

    /**
     * 格式化double保留两位小数点
     *
     * @param value
     * @return
     */
    public static String decimalFormat2(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(value);
    }

    public static String decimalFormat0(String value) {
        double aDouble = 0.00;
        try {
            aDouble = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return value;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0");
        return decimalFormat.format(aDouble);
    }


    public static String decimalFormat2(String value) {
        double aDouble = 0.00;
        try {
            aDouble = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return value;
        }
        return decimalFormat2(aDouble);
    }

    /**
     * 为textview 设置文字
     *
     * @param textView
     * @param resId
     * @param formatArgs
     */
    public static void setTextByResId(TextView textView, @StringRes int resId, Object... formatArgs) {

        textView.setText(Utils.getApp().getResources().getString(resId, formatArgs));
    }


    /**
     * 为textview 设置文字
     *
     * @param textView
     * @param resId
     * @param formatArgs
     */
    public static void setTextHttpByResId(TextView textView, @StringRes int resId, Object... formatArgs) {

        textView.setText(Html.fromHtml(Utils.getApp().getResources().getString(resId, formatArgs)));
    }

    /**
     * 为textview 设置文字
     *
     * @param textView
     * @param resId
     */
    public static void setTextByResId(TextView textView, @StringRes int resId) {

        textView.setText(Utils.getApp().getResources().getString(resId));
    }

    public static void setTextColor(TextView textView, @ColorRes int resId) {
        textView.setTextColor(Utils.getApp().getResources().getColor(resId));
    }

    public static String ByteToString(byte[] bytes)
    {

        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i <bytes.length ; i++) {
            if (bytes[i]!=0){
                strBuilder.append((char)bytes[i]);
            }else {
                break;
            }

        }
        return strBuilder.toString();
    }




    /**
     * 获取订单号
     * @param tag
     * @return
     */
    public static String getOrderId(String tag){
        return tag + String.valueOf(System.currentTimeMillis());
    }

    private static long mExitTime;
    public static boolean doubleClickExit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            RxToast.showToast("再按一次退出");
            mExitTime = System.currentTimeMillis();
            return false;
        }
        return true;
    }

}
