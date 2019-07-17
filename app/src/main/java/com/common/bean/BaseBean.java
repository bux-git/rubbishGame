package com.common.bean;

/**
 * @description：
 * @author：bux on 2018/9/10 23:12
 * @email: 471025316@qq.com
 */
public class BaseBean<T> {


    public BaseBean() {
    }

    public BaseBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * state : 1
     * msg : 成功
     * data : {"userName":"七区","Phone":"111111111"}
     */



    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public boolean isSuccess() {
        return code == 1;
    }


    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", msg='" + msg +
                '}';
    }
}
