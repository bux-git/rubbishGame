package com.common.bean;

import java.util.List;

public  class MsgResultBean<T> {
        /**
         * data : []
         * paycode : 1
         * payurl : http://pay.goliebao.com/api/wxinpay/example/jspay.php?money=10&out_trade_no=test001&body=123
         */
        private int paycode;
        private String payurl;
        private List<T> data;

        public int getPaycode() {
            return paycode;
        }

        public void setPaycode(int paycode) {
            this.paycode = paycode;
        }

        public String getPayurl() {
            return payurl;
        }

        public void setPayurl(String payurl) {
            this.payurl = payurl;
        }

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }
    }
