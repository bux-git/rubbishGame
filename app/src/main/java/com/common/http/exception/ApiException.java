package com.common.http.exception;

/**
 * @description：
 * @author：bux on 2018/4/18 11:23
 * @email: 471025316@qq.com
 */
public class ApiException extends BaseException {

    public String data;
    public ApiException(int code, String displayMessage,String data) {
        super(code, displayMessage);
        this.data=data;
    }
}
