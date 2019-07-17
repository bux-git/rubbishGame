package com.common.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.zx.rubbishgame.R;


/**
 * @author Vondear
 * @date 2017/3/16
 */

public class RxDialogLoading extends RxDialog {

    private SpinKitView mLoadingView;
    private View mDialogContentView;
    private TextView mTextView;

    public RxDialogLoading(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    public RxDialogLoading(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    public RxDialogLoading(Context context) {
        super(context);
        initView(context);
    }

    public RxDialogLoading(Activity context) {
        super(context);
        initView(context);
    }

    public RxDialogLoading(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView(context);
    }

    private void initView(Context context) {
        mDialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_loading_spinkit, null);
        mLoadingView = mDialogContentView.findViewById(R.id.spin_kit);
        mTextView = mDialogContentView.findViewById(R.id.name);
        setContentView(mDialogContentView);
    }

    public void setLoadingText(CharSequence charSequence) {
        mTextView.setText(charSequence);
    }

    public void setLoadingColor(int color){
        mLoadingView.setColor(color);
    }


    public void cancel(String str) {
        cancel();
    }

    public SpinKitView getLoadingView() {
        return mLoadingView;
    }

    public View getDialogContentView() {
        return mDialogContentView;
    }

    public TextView getTextView() {
        return mTextView;
    }
}