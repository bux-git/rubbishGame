package com.zx.rubbishgame.banner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * description：
 * author：bux on 2019/5/23 11:02
 * email: 471025316@qq.com
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected T t;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(T data) {
        t = data;
        setUpData(data);
    }

     abstract void setUpData(T t);
}
