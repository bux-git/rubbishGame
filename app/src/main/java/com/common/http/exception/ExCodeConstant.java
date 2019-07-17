package com.common.http.exception;

/**
 * @description：
 * @author：bux on 2018/4/18 16:29
 * @email: 471025316@qq.com
 */
public class ExCodeConstant {
    /*API错误*/
    public static final int API_ERROR = 10;

    /*网络错误*/
    public static final int NETWORD_ERROR =111;
    /*http_错误*/
    public static final int HTTP_ERROR = 11;
    /*json错误*/
    public static final int JSON_ERROR = 13;
    public static final int MALFORMED_JSON_EXCEPTION=14;
    /*未知错误*/
    public static final int UNKNOWN_ERROR = 15;
    /*运行时异常-包含自定义异常*/
    public static final int RUNTIME_ERROR = 16;
    /*无法解析该域名*/
    public static final int UNKOWNHOST_ERROR = 17;

    /*连接网络超时*/
    public static final int SOCKET_TIMEOUT_ERROR = 18;

    /*无网络连接*/
    public static final int SOCKET_ERROR = 19;


    //    api

    // 服务器错误
    public static final int ERROR_API_SYSTEM = 10000;

    // 登录错误，用户名密码错误
    public static final int ERROR_API_LOGIN = 10001;

    //调用无权限的API
    public static final int ERROR_API_NO_PERMISSION = 10002;

    //账户被冻结
    public static final int ERROR_API_ACCOUNT_FREEZE = 10003;


    //Token 失效
    public static final int ERROR_TOKEN = 110000;


    // http

    public static final int ERROR_HTTP_400 = 400;

    public static final int ERROR_HTTP_404 = 404;

    public static final int ERROR_HTTP_405 = 405;

    public static final int ERROR_HTTP_500 = 500;


}
