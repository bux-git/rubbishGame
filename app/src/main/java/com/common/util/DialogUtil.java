package com.common.util;

import android.content.Context;

import com.common.dialog.CustomDialog;


/**
 * @description：
 * @author：bux on 2018/6/8 10:19
 * @email: 471025316@qq.com
 */
public class DialogUtil {


    public static CustomDialog showLRDialog(Context context, String title, String content, CustomDialog.OnCustomClickListener left, CustomDialog.OnCustomClickListener right) {

        return showLRDialog(context, title, content, "取消", "确定", left, right);
    }

    /**
     * 打印失败提示
     * @param context
     * @param title
     * @param content
     * @param right
     * @return
     */
    public static CustomDialog showPrintFailed(Context context, String content, CustomDialog.OnCustomClickListener left, CustomDialog.OnCustomClickListener right) {
        CustomDialog customDialog = showLRDialog(context, "温馨提示", content, "暂不打印", "重新打印", left, right);
        customDialog.setCancelable(false);
        customDialog.setCanceledOnTouchOutside(false);

        return customDialog;
    }

    public static CustomDialog showLRDialog(Context context, String title, String content, String cancel, String submit, CustomDialog.OnCustomClickListener left, CustomDialog.OnCustomClickListener right) {
        CustomDialog confirmDailog = new CustomDialog(context);
        confirmDailog
                .setTitleText(title)
                .setContentText(content)
                .setCancelText(cancel)
                .setConfirmText(submit)
                .setCancelClickListener(left)
                .setConfirmClickListener(right);
        return confirmDailog;
    }



}
