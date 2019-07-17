package com.common.dialog;

import android.view.ViewGroup;

/**
 * @description：
 * @author：bux on 2019/4/8 18:09
 * @email: 471025316@qq.com
 */
public abstract class FullDialog extends BaseCenterDialog {


    @Override
    public int getWidth() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }



    @Override
    public int getHeight() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

}
