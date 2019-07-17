package com.common.http.rx.subscriber;

import android.content.Context;
import android.content.DialogInterface;

import com.common.dialog.RxDialogLoading;

import org.reactivestreams.Subscription;

/**
 * @description：
 * @author：bux on 2018/4/19 11:48
 * @email: 471025316@qq.com
 */
public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T> implements DialogInterface.OnCancelListener {

    private RxDialogLoading mProgressDialog;
    private boolean isShow = true;

    private Subscription mSubscription;


    public ProgressDialogSubscriber(Context context, boolean isShow, boolean isShowCancel) {
        this.isShow = isShow;
        if (isShow) {
            mProgressDialog = new RxDialogLoading(context, isShowCancel, this);
        }
    }

    protected ProgressDialogSubscriber(Context context) {
        this(context, true, false);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        super.onSubscribe(subscription);
        mSubscription=subscription;
        if (isShow) {
            mProgressDialog.show();
        }
    }


    @Override
    public void onError(Throwable t) {
        super.onError(t);
        dismissDialog();
    }

    @Override
    public void onComplete() {
        super.onComplete();
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
