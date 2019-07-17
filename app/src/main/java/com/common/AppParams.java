package com.common;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;

/**
 * @description：APP级别卸载才删除
 * @author：bux on 2018/6/25 9:57
 * @email: 471025316@qq.com
 */
public class AppParams {


    private static final String FILE_NAME = "AppParams";
    private static SPUtils sp;


    public static void init() {
        sp = SPUtils.getInstance(FILE_NAME, Context.MODE_PRIVATE);

    }


}
