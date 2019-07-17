package com.common.http;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Description：
 * @author：Bux on 2018/1/10 11:05
 * @email: 471025316@qq.com
 */

public interface ApiService {


    @FormUrlEncoded
    @POST
    Flowable<String> postMap(@Url String url, @FieldMap Map<String, String> params);

    @GET
    Flowable<String> getQuery(@Url String url, @QueryMap Map<String, String> params);

    /**
     * 通过 MultipartBody和@body作为参数来上传
     *
     * @param multipartBody MultipartBody包含多个Part
     * @return 状态信息
     */
    @POST
    Flowable<String> uploadWithRequestBody(@Url String url, @Body MultipartBody multipartBody);

}
