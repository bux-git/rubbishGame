package com.common.http.exception;

import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.zx.rubbishgame.R;


/**
 *
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.common.exception
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class ErrorMessageFactory {

    public static String create(int code) {
        Context context = Utils.getApp();

        String errorMsg = null;


        switch (code) {

            case ExCodeConstant.HTTP_ERROR:
                errorMsg = context.getResources().getString(R.string.error_http);
                break;

            case ExCodeConstant.SOCKET_TIMEOUT_ERROR:

                errorMsg = context.getResources().getString(R.string.error_socket_timeout);

                break;
            case ExCodeConstant.SOCKET_ERROR:

                errorMsg = context.getResources().getString(R.string.error_socket_unreachable);

                break;
            case ExCodeConstant.JSON_ERROR:

                errorMsg = context.getResources().getString(R.string.error_malformedJsonException);

                break;


            case ExCodeConstant.ERROR_HTTP_400:
                errorMsg = context.getResources().getString(R.string.error_http_400);

                break;


            case ExCodeConstant.ERROR_HTTP_404:
                errorMsg = context.getResources().getString(R.string.error_http_404);

                break;

            case ExCodeConstant.ERROR_HTTP_500:
                errorMsg = context.getResources().getString(R.string.error_http_500);

                break;

            case ExCodeConstant.ERROR_API_SYSTEM:
                errorMsg = context.getResources().getString(R.string.error_system);
                break;

            case ExCodeConstant.ERROR_API_ACCOUNT_FREEZE:
                errorMsg = context.getResources().getString(R.string.error_account_freeze);
                break;


            case ExCodeConstant.ERROR_API_NO_PERMISSION:
                errorMsg = context.getResources().getString(R.string.error_api_no_perission);
                break;

            case ExCodeConstant.ERROR_API_LOGIN:
                errorMsg = context.getResources().getString(R.string.error_login);
                break;

            case ExCodeConstant.ERROR_TOKEN:
                errorMsg = context.getResources().getString(R.string.error_token);
                break;

            default:
                errorMsg = context.getResources().getString(R.string.error_unkown);
                break;


        }


        return errorMsg;


    }
}
