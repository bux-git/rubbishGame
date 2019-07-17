package com.common;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;

/**
 * @description：全局设置参数
 * @author：bux on 2018/5/27 0:49
 * @email: 471025316@qq.com
 */
public class GlobalParams {

    private static final String FILE_NAME = "GlobalParams";

    private static SPUtils sp;


    public static void init() {
        sp = SPUtils.getInstance(FILE_NAME, Context.MODE_PRIVATE);
        AppParams.init();
    }




    public static void clear() {
        sp.clear();
        init();
    }

}
