package com.common.http.rx.subscriber;


import com.common.http.exception.BaseException;
import com.common.http.exception.RxErrorHandler;
import com.common.util.L;

/**
 * @description：
 * @author：bux on 2018/4/18 16:26
 * @email: 471025316@qq.com
 */
public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {

    private static final String TAG = "ErrorHandlerSubscriber";
    private RxErrorHandler mRxErrorHandler;

    protected ErrorHandlerSubscriber() {
        mRxErrorHandler = new RxErrorHandler();
    }



    @Override
    public void onError(Throwable t) {
        L.e("",t);
        BaseException exception = mRxErrorHandler.handlerError(t);
        showErrorMsg(exception);
        /*if (exception.getDisplayMessage().contains("token过期")
                ||exception.getDisplayMessage().contains("无法获取token")) {

            LoginUtil.reLogin();
        }*/

        onFail(exception);
        onAfter();
        
    }

    public void showErrorMsg(BaseException exception) {
        mRxErrorHandler.showErrorMessage(exception);
    }

    public  void onFail(BaseException e){

    }

    @Override
    public void onComplete() {
        onAfter();
    }

    public void onAfter() {

    }


}
