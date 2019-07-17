package com.common.bean;

/**
 * @description：
 * @author：bux on 2019/4/3 17:47
 * @email: 471025316@qq.com
 */
public class ResultBean<T> {
    /**
     * msg_code : 1
     * msg_info : Request Success
     * msg_result : {"data":[],"paycode":1,"payurl":"http://pay.goliebao.com/api/wxinpay/example/jspay.php?money=10&out_trade_no=test001&body=123"}
     */
    private int msg_code;
    private String msg_info;
    private MsgResultBean<T> msg_result;


    public int getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(int msg_code) {
        this.msg_code = msg_code;
    }

    public String getMsg_info() {
        return msg_info;
    }

    public void setMsg_info(String msg_info) {
        this.msg_info = msg_info;
    }

    public MsgResultBean<T> getMsg_result() {
        return msg_result;
    }

    public void setMsg_result(MsgResultBean<T> msg_result) {
        this.msg_result = msg_result;
    }

    public boolean isSuccess() {
        return msg_code == 1;
    }


}
