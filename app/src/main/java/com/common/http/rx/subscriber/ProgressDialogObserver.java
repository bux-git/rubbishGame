package com.common.http.rx.subscriber;

import android.content.Context;
import android.content.DialogInterface;

import com.common.dialog.RxDialogLoading;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @description：
 * @author：bux on 2018/4/19 11:48
 * @email: 471025316@qq.com
 */
public abstract class ProgressDialogObserver<T> implements Observer<T>, DialogInterface.OnCancelListener {

    private RxDialogLoading mProgressDialog;
    private boolean isShow = true;

    private Disposable mDisposable;


    public ProgressDialogObserver(Context context, boolean isShow, boolean isShowCancel, String msg) {
        this.isShow = isShow;
        if (isShow) {
            mProgressDialog = new RxDialogLoading(context, isShowCancel, this);
            mProgressDialog.setLoadingText(msg);
        }
    }

    protected ProgressDialogObserver(Context context, String msg) {
        this(context, true, false, msg);
    }

    protected ProgressDialogObserver(Context context) {
        this(context, true, false, "");
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        if (isShow) {
            mProgressDialog.show();
        }
    }

    @Override
    public void onError(Throwable t) {
        dismissDialog();
    }

    @Override
    public void onComplete() {
        dismissDialog();
    }

    protected void dismissDialog() {
        if (isShow) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void onCancel(DialogInterface dialog) {


    }
}
