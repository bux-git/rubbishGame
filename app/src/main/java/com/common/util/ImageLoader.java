package com.common.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.common.constant.Constant;
import com.zx.rubbishgame.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * .封装Glide图片加载
 *
 * @author Juny
 * @date 2017/9/12/012
 */

public class ImageLoader {

    public static final int DEFAULT_IMG= R.drawable.ic_launcher_background;

    public static void loadNewsImg(Object url, ImageView view) {
        Glide.with(view).load(handleImage(url))
                .apply(new RequestOptions()
                        .placeholder(DEFAULT_IMG)
                        .error(DEFAULT_IMG)
                ).into(view);
    }


    public static void load(Object url, ImageView view) {
        Glide.with(view).load(handleImage(url))
                .apply(new RequestOptions()
                        .placeholder(DEFAULT_IMG)
                        .error(DEFAULT_IMG)
                ).into(view);
    }

    public static void load(Object url, ImageView view, int defaultDrawable) {
        Glide.with(view).load(handleImage(url))
                .apply(new RequestOptions()
                        .placeholder(defaultDrawable)
                        .error(defaultDrawable)
                ).into(view);
    }


    public static void loadResultDefault(Object url, ImageView view, int defaultDrawable, SimpleTarget<Drawable> target) {
        Glide.with(view).load(handleImage(url))
                .apply(new RequestOptions()
                        .placeholder(defaultDrawable)
                        .error(defaultDrawable)
                ).into(target);
    }


    public static void loadNoDiskCache(Object url, ImageView view) {
        Glide.with(view).load(handleImage(url))
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(DEFAULT_IMG)
                        .error(DEFAULT_IMG)
                ).into(view);
    }

    /**
     * 处理短路径图片地址
     *
     * @param url 原始地址
     * @return 处理后的地址
     */
    private static Object handleImage(Object url) {
        if (url instanceof String) {
            if (((String) url).startsWith("http")
                    || ((String) url).startsWith("https")) {
                return url;
            } else {
                return Constant.IMAGE_URL + url;
            }
        }
        return url;
    }

    public static void loadResultDefaultWhite(RequestManager glide, Object url, SimpleTarget<Drawable> target) {
        glide.load(url)
                .apply(new RequestOptions()
                        .placeholder(DEFAULT_IMG)
                        .error(DEFAULT_IMG))
                .into(target);
    }

    /**
     * 加载毛玻璃效果
     */
    public static void loadBlurResult(RequestManager glide, Object url, SimpleTarget<Drawable> target) {
        glide.load(url)
                .apply(bitmapTransform(new BlurTransformation(25, 3)))
                .apply(new RequestOptions()
                        .placeholder(DEFAULT_IMG)
                        .error(DEFAULT_IMG))
                .into(target);
    }

/*    *//**
     * 加载圆形图片
     *//*
    public static void loadResultCircle(Object url, ImageView view) {
        Glide.with(view).load(handleImage(url))
                .apply(new RequestOptions()
                        .circleCrop()
                        .placeholder(R.drawable.ic_header)
                        .error(R.drawable.ic_header))
                .into(view);
    }*/

 /*   *//**
     * 加载圆形头像
     *//*
    public static void loadHeadCircle(Object url, ImageView view) {
        loadLocalHeadCircle(handleImage(url), view);
    }*/

/*    *//**
     * 加载圆形头像
     *//*
    public static void loadLocalHeadCircle(Object url, ImageView view) {
        Glide.with(view).load(url)
                .apply(new RequestOptions()
                        .circleCrop()
                        //头像地址没变 所以加一个key
                        .signature(new ObjectKey(GlobalParams.sUser.getUpdateDate()))
                        .placeholder(R.drawable.ic_header)
                        .error(R.drawable.ic_header))
                .into(view);
    }*/

    public static void loadResultCircle(RequestManager glide, Object url, SimpleTarget<Drawable> target) {
        glide.load(url)
                .apply(new RequestOptions()
                        .circleCrop()
                        .placeholder(DEFAULT_IMG)
                        .error(DEFAULT_IMG))
                .into(target);
    }

    /**
     * 加载圆角图片
     */
    public static void loadResultRounded(RequestManager glide, Object url, SimpleTarget<Drawable> target) {
        glide.load(url)
                .apply(bitmapTransform(new RoundedCornersTransformation(10, 0)))
                .apply(new RequestOptions()
                        .placeholder(DEFAULT_IMG)
                        .error(DEFAULT_IMG))
                .into(target);
    }

    public static void loadResultRounded(RequestManager glide, Object url, ImageView view) {
        glide.load(url)
                .apply(bitmapTransform(new RoundedCornersTransformation(10, 0)))
                .apply(new RequestOptions()
                        .placeholder(DEFAULT_IMG)
                        .error(DEFAULT_IMG))
                .into(view);
    }

    public static void loadLocal(String url, ImageView image) {
        Glide.with(image).load(handleImage(url))
                .apply(new RequestOptions()
                        .placeholder(DEFAULT_IMG)
                        .error(DEFAULT_IMG)
                ).into(image);
    }

    public static void clear(Context context) {

    }
}
