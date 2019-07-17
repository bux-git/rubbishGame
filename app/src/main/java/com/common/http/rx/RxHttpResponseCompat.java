package com.common.http.rx;


import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.common.bean.BaseBean;
import com.common.bean.MsgResultBean;
import com.common.bean.ResultBean;
import com.common.http.exception.ApiException;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: bux on 2018/4/18 11:00
 * @email: 471025316@qq.com
 * @description:统一封装结果预处理
 */
public class RxHttpResponseCompat {


    public static <T> FlowableTransformer<String, MsgResultBean<T>> compatResult(final Class<T> cls) {

        return new FlowableTransformer<String, MsgResultBean<T>>() {
            @Override
            public Publisher<MsgResultBean<T>> apply(Flowable<String> upstream) {
                return upstream.flatMap(new Function<String, Flowable<MsgResultBean<T>>>() {
                    @Override
                    public Flowable<MsgResultBean<T>> apply(String result) throws Exception {

                        result = result.replace("[]", "");
                       // L.d(result);
                        ResultBean<T> t = GsonUtils.fromJson(result,GsonUtils.getType(ResultBean.class,cls) );
                        //成功
                        if (t.isSuccess()) {
                            return Flowable.just(t.getMsg_result());
                        } else {//失败
                            return Flowable.error(new ApiException(t.getMsg_code(), t.getMsg_info(), result));
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> FlowableTransformer<String, List<T>> compatResultList(final Class<T> cls) {

        return new FlowableTransformer<String, List<T>>() {
            @Override
            public Publisher<List<T>> apply(Flowable<String> upstream) {
                return upstream.flatMap(new Function<String, Publisher<List<T>>>() {
                    @Override
                    public Publisher<List<T>> apply(String result) throws Exception {

                        if ("[]".equals(result) || TextUtils.isEmpty(result)) {
                            result = "{\"code\":1,\"msg\":\"无数据\",\"data\":[]}";
                        } else {

                            if (result.startsWith("[")) {

                                result = "{\"code\":1,\"msg\":\"成功\",data:" + result + "}";
                                result = result.replace(",\"regList\":\"\"", ",\"regList\":[]");
                                result = result.replace(",\"regList\":null", ",\"regList\":[]");
                            }
                        }

                       // L.d(result);

                        BaseBean<List<T>> t = GsonUtils.fromJson(result, GsonUtils.getType(BaseBean.class, GsonUtils.getListType(cls)));
                        return getFlowable(result, t);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    private static <T> Publisher<T> getFlowable(String result, BaseBean<T> t) {
        //成功
        if (t.isSuccess()) {
            return Flowable.just(t.getData());
        } else {//失败
            String msg = TextUtils.isEmpty(t.getMsg()) ? t.getMsg() : t.getMsg();
            return Flowable.error(new ApiException(t.getCode(), msg, result));
        }
    }


}






