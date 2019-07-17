package com.common.http.exception;

/**
 * @description：
 * @author：bux on 2018/4/18 11:21
 * @email: 471025316@qq.com
 */
public class BaseException extends Exception {


    private int code;
    private String displayMessage;

    public BaseException(int code, String displayMessage) {
        super(displayMessage);
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public BaseException() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
