package com.common.di.component;


import com.common.di.module.AppModule;
import com.common.di.module.HttpModule;
import com.common.http.ApiService;
import com.danikula.videocache.HttpProxyCacheServer;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * @description：
 * @author：bux on 2018/4/2 16:43
 * @email: 471025316@qq.com
 */

@Component(modules = {AppModule.class, HttpModule.class})
@Singleton
public interface AppComponent {

    ApiService getApiService();


    Gson getGson();

    OkHttpClient getOkHttpClient();

    HttpProxyCacheServer getProxyCacheServer();

}
