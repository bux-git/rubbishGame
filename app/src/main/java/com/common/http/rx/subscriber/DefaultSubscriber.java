package com.common.http.rx.subscriber;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;

/**
 * @description：
 * @author：bux on 2018/4/18 16:24
 * @email: 471025316@qq.com
 */
public abstract class DefaultSubscriber<T> implements FlowableSubscriber<T> {

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Integer.MAX_VALUE);
    }

    @Override
    public void onError(Throwable e) {

    }
}
