package com.common.http.util;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @description：添加公共参数将原来请求参数转换成接口规范参数格式并添加公共参数
 * @author：bux on 2018/4/23 11:54
 * @email: 471025316@qq.com
 */
public class CommonParamsInterceptor implements Interceptor {


    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {


        Request oldRequest = chain.request();
        Request newRequest = null;
        String method = oldRequest.method();


        if ("POST".equals(method)) {

            /**
             * post 方式一共有三种body 对应三种请求参数方式
             * 1.post 直接post数据流 对应RequestBody
             * 2.form 提交 key=value&key2=value2 串方式 FormBody
             * 3.MultipartBody 提交多类型方式 图文混排 或者文件 混排等等 MultipartBody
             */
            RequestBody body = oldRequest.body();
            if (body instanceof FormBody) {

                FormBody formBody = (FormBody) body;

                FormBody.Builder builder = new FormBody.Builder();

                for (int i = 0; i < formBody.size(); i++) {

                    builder.addEncoded(formBody.name(i), formBody.value(i));
                }


                //构建新的请求
                newRequest = oldRequest
                        .newBuilder()
                        .post(builder.build())
                        .build();
            }


        }

        Response response;

        if (newRequest != null) {
            //重新组装Response 使得日志拦截器可以输出修改后的参数
            Response networkResponse = chain.proceed(newRequest);
            Response.Builder responseBuilder = networkResponse.newBuilder()
                    .request(oldRequest);
            response = responseBuilder.build();
        } else {

            return chain.proceed(oldRequest);

        }


        return response;
    }

}
