package com.common.http;

import com.common.bean.MsgResultBean;
import com.common.constant.Constant;
import com.common.http.rx.RxHttpResponseCompat;
import com.common.util.CommonUtil;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * @description：
 * @author：bux on 2018/6/9 9:31
 * @email: 471025316@qq.com
 */
public class BaseModel<E> {

    public static final int POST=1;
    public static final int GET=2;
    public static final int POST_MUL=3;


    private ApiService mApiService ;
    private LifecycleProvider<E> rxLife;

    protected BaseModel(LifecycleProvider<E> rxLife,ApiService apiService) {
        mApiService=apiService;
        this.rxLife = rxLife;
    }


    /**
     * 根据提交方式获取
     * @param action
     * @param map
     * @param builder
     * @param method
     * @param isLife
     * @return
     */
    private Flowable<String> getBaseApi(String action, Map<String, String> map,MultipartBody.Builder builder, int method,boolean isLife) {
        if (map == null) {
            map = new HashMap<>();
        }
        String url=commonApiParams(action, map);

        Flowable<String> flowable=mApiService.postMap(url, map);
        switch (method) {
            case GET:
                flowable=mApiService.getQuery(url, map);
                break;
            case POST_MUL:
                builder.setType(MultipartBody.FORM);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    builder.addFormDataPart(entry.getKey(), entry.getValue());
                }
                flowable=mApiService.uploadWithRequestBody(url, builder.build());
                break;
                default:

        }
        if(!isLife){
            return flowable;
        }
        return flowable.compose(rxLife.<String>bindToLifecycle());
    }


    /**
     * post 请求接口
     *
     * @param <T>
     * @param action
     * @param map
     * @param cls
     * @return
     */
    protected <T> Flowable<MsgResultBean<T>> postField(String action, Map<String, String> map, Class<T> cls) {
        if (map == null) {
            map = new HashMap<>();
        }
        return getBaseApi(action,map,null,POST,true)
                .compose(RxHttpResponseCompat.compatResult(cls));
    }

    /**
     * get请求接口
     *
     * @param action
     * @param map
     * @param cls
     * @param <T>
     * @return
     */
    protected <T> Flowable<List<T>> getQueryList(String action, Map<String, String> map, Class<T> cls) {

          return getBaseApi(action,map,null,GET,true)
                .compose(RxHttpResponseCompat.compatResultList(cls));
    }


    /**
     * get请求接口
     *
     * @param action
     * @param map
     * @param cls
     * @param <T>
     * @return
     */
    protected <T> Flowable<MsgResultBean<T>> getQuery(String action, Map<String, String> map, Class<T> cls) {
        return getBaseApi(action, map, null, GET, true)
                .compose(RxHttpResponseCompat.compatResult(cls));
    }



        /**
         * get请求接口
         *
         * @param action
         * @param map
         * @return
         */
    Flowable<String> getQuery(String action, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        return getBaseApi(action,map,null,GET,false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }







    private static String commonApiParams(String action, Map<String, String> map) {
        String url = action;
        if (!action.contains("http")) {
            url = Constant.GLOBAL_BASE_URL + action;
        }

        commonParams(map);

        return url;
    }

    /**
     * 设置公共参数
     *
     * @param map
     * @return
     */
    private static void commonParams(Map<String, String> map) {

        //设置公共参数
       // map.put("platform", "1");
       // map.put("version", BuildConfig.VERSION_NAME);

    }

    /**
     * 根据action url 拼接处完整的URL和公共参数
     *
     * @param action
     * @return
     */
    public static String commonParamsUrl(String action, Map<String, String> map) {


        String url = action;
        if (!url.contains("http")) {
            url = Constant.BASE_URL + action;
        }

        String split = "";
        if (url.contains("?")) {
            split = "&";
        } else {

            split = "?";
        }

        if (map == null) {
            map = new HashMap<>();
        }

        //没有token才添加token
        if (!url.contains("token=")) {
            commonParams(map);
        }


        if (map.size() == 0) {
            return url;
        }
        return url + split + CommonUtil.paramsToGet(map);
    }

    /**
     * 根据action 拼接处完整的URL和公共参数
     *
     * @param action
     * @return
     */
    public static String commonParamsUrl(String action) {

        return commonParamsUrl(action, null);
    }

    /**
     * 将带参数的url中的token 替换掉
     *
     * @param url
     * @return
     */
    public static String commonReplaceToken(String url) {
        if (!url.contains("?")) {
            return commonParamsUrl(url);
        }

        int indexW = url.indexOf("?");
        StringBuilder realUrl = new StringBuilder(url.substring(0, indexW));
        String paramsStr = url.substring(indexW, url.length());

        String[] params = paramsStr.split("&");

        //重新组合
        realUrl.append("?");
        String split = "";
        for (String param : params) {
            if (!param.contains("token")) {
                realUrl.append(split).append(param);
                split = "&";
            }
        }

       // realUrl.append("&token=").append(GlobalParams.sToken);

        return realUrl.toString();
    }


}
