package com.common.di.module;

import com.danikula.videocache.HttpProxyCacheServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.zx.rubbishgame.MyApplication;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @description：
 * @author：bux on 2018/4/2 16:11
 * @email: 471025316@qq.com
 */

@Module
public class AppModule {

    private MyApplication mApplication;

    public AppModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    MyApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(
                        new TypeToken<Map<String, Object>>() {
                        }.getType(),
                        new JsonDeserializer<Map<String, Object>>() {
                            @Override
                            public Map<String, Object> deserialize(
                                    JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {

                                Map<String, Object> treeMap = new HashMap<String, Object>();
                                JsonObject jsonObject = json.getAsJsonObject();
                                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                                for (Map.Entry<String, JsonElement> entry : entrySet) {
                                    treeMap.put(entry.getKey(), entry.getValue());
                                }
                                return treeMap;
                            }
                        }).create();
    }


    @Provides
    @Singleton
    HttpProxyCacheServer provideProxyCache(MyApplication application){
        return new HttpProxyCacheServer.Builder(application)
                .maxCacheFilesCount(10)
                .build();
    }


}
