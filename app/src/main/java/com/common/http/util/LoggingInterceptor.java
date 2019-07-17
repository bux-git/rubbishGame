package com.common.http.util;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 日志监控
 *
 * @author Juny
 * @date 2018/1/27/027
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        long t1 = System.nanoTime();

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();//收到响应的时间

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        String method = request.method();
        StringBuilder sb = new StringBuilder();
        if ("POST".equals(method)) {
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    if ("apikey".equals(body.encodedName(i))) {
                        //continue;
                    }
                    sb.append("&").append(body.encodedName(i)).append("=").append(body.value(i));
                }
            }
        }
        Logger.i(request.url().toString() + sb.toString() + "\n" + "响应时间" + (t2 - t1) / 1e6d + "ms");
        //Logger.json(responseBody.string());
        return response;
    }
}
