package com.common.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @description：
 * @author：bux on 2018/6/25 18:35
 * @email: 471025316@qq.com
 */
public class PermissionUtils {


    /**
     * 检查电话权限
     *
     * @param activity
     * @param consumer
     */
    @SuppressLint("CheckResult")
    public static void checkCallPhone(Activity activity, Consumer<Boolean> consumer) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.CALL_PHONE)
                .subscribe(consumer);
    }


    /**
     * 检查电话权限
     *
     * @param activity
     * @param consumer
     */
    @SuppressLint("CheckResult")
    public static void checkPhoneState(Activity activity, Consumer<Boolean> consumer) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(consumer);
    }

    /**
     * WRITE_EXTERNAL_STORAGE
     */
    @SuppressLint("CheckResult")
    public static void checkWriteStorage(Activity activity, Consumer<Boolean> consumer) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(consumer);
    }

    /**
     * WRITE_EXTERNAL_STORAGE
     */
    @SuppressLint("CheckResult")
    public static void checkWriteStorage(Activity activity) {

        checkWriteStorage(activity, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {

            }
        });
    }


    /**
     * CAMERA
     */
    @SuppressLint("CheckResult")
    public static void checkCamera(Activity activity, Consumer<Boolean> consumer) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(consumer);
    }



    /**
     * CAMERA
     */
    @SuppressLint("CheckResult")
    public static void checkCameraVideo(Activity activity, Consumer<Boolean> consumer) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(consumer);
    }


    /**
     * CAMERA
     */
    @SuppressLint("CheckResult")
    public static void checkCameraVideo(Activity activity) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
