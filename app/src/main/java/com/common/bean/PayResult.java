package com.common.bean;

/**
 * @description：
 * @author：bux on 2019/4/1 15:43
 * @email: 471025316@qq.com
 */
public class PayResult {


    /**
     * openid : ooD8r1nl4NUB6RHsI07wa9wRTQTg
     * out_trade_no : sz0100m39912019040314430501n0huy
     * time_end : 2019-04-03 14:43:19
     * total_fee : 23800
     * transaction_id : 4200000280201904039685055402
     * way : WeChat
     * result_code : SUCCESS
     */

    private String openid;
    private String out_trade_no;
    private String time_end;
    private String total_fee;
    private String transaction_id;
    private String way;
    private String result_code;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }
}
