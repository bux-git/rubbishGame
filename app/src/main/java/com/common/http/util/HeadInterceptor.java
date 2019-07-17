package com.common.http.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @description：
 * @author：bux on 2018/5/25 16:17
 * @email: 471025316@qq.com
 */
public class HeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request builder = chain.request().newBuilder()
               .addHeader("Accept", "*/*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Host", "be02.bihu.com")
                .addHeader("Origin", "https://bihu.com")
                .addHeader("Referer", "https://bihu.com/?category=follow")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "6f44a38a-b172-4b7d-830d-2d01d243bc64")
                .build();

        return chain.proceed(builder);

    }
}
