package com.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.view.Gravity;
import android.widget.Toast;

import com.blankj.utilcode.util.Utils;


/**
 * @author vondear
 * 在系统的Toast基础上封装
 */

@SuppressLint("InflateParams")
public class RxToast {

    @ColorInt
    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");

    @ColorInt
    private static final int ERROR_COLOR = Color.parseColor("#606060");

    @ColorInt
    private static final int INFO_COLOR = Color.parseColor("#606060");

    @ColorInt
    private static final int SUCCESS_COLOR = Color.parseColor("#606060");

    @ColorInt
    private static final int WARNING_COLOR = Color.parseColor("#606060");

   /* @DrawableRes
    private static final int ERROR_DRAWABLE= R.drawable.ic_toast_error;
    @DrawableRes
    private static final int WARNING_DRAWABLE=R.drawable.ic_error_outline_white_48dp;
    @DrawableRes
    private static final int SUCCESS_DRAWABLE=R.drawable.ic_toast_right;
    @DrawableRes
    private static final int INFO_DRAWABLE=R.drawable.ic_info_outline_white_48dp;


    private static final int TOAST_BACKUP=R.drawable.shape__606060_corner_12dp;*/

    private static final String TOAST_TYPEFACE = "sans-serif-condensed";

    private static Toast currentToast;

    //*******************************************普通 使用ApplicationContext 方法*********************
    /**
     * Toast 替代方法 ：立即显示无需等待
     */
    private static Toast mToast;


   /* public static void normal(@NonNull String message) {
        normal(getContext(), message, Toast.LENGTH_SHORT, null, false).show();
    }

    public static void normal(@NonNull String message, Drawable icon) {
        normal(getContext(), message, Toast.LENGTH_SHORT, icon, true).show();
    }

    public static void normal(@NonNull String message, int duration) {
        normal(getContext(), message, duration, null, false).show();
    }

    public static void normal(@NonNull String message, int duration, Drawable icon) {
        normal(getContext(), message, duration, icon, true).show();
    }

    public static Toast normal(@NonNull String message, int duration, Drawable icon, boolean withIcon) {
        return custom(getContext(), message, icon, DEFAULT_TEXT_COLOR, duration, withIcon);
    }

    public static void warning(@NonNull String message) {
        warning(getContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    public static void warning(@NonNull String message, int duration) {
        warning(getContext(), message, duration, true).show();
    }

  *//*  public static Toast warning(@NonNull String message, int duration, boolean withIcon) {
        return custom(getContext(), message, getDrawable(getContext(), WARNING_DRAWABLE), DEFAULT_TEXT_COLOR, WARNING_COLOR, duration, withIcon, true);
    }*//*

    public static void info(@NonNull String message) {
        info(getContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    public static void info(@NonNull String message, int duration) {
        info(getContext(), message, duration, true).show();
    }

   *//* public static Toast info(@NonNull String message, int duration, boolean withIcon) {
        return custom(getContext(), message, getDrawable(getContext(),INFO_DRAWABLE), DEFAULT_TEXT_COLOR, INFO_COLOR, duration, withIcon, true);
    }*//*

    public static void success(@NonNull String message) {
        success(getContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    public static void success(@NonNull String message, int duration) {
        success(getContext(), message, duration, true).show();
    }

    public static Toast success(@NonNull String message, int duration, boolean withIcon) {
        return custom(getContext(), message, getDrawable(getContext(),SUCCESS_DRAWABLE), DEFAULT_TEXT_COLOR, SUCCESS_COLOR, duration, withIcon, true);
    }

    public static void error(@NonNull String message) {
        error(getContext(), message, Toast.LENGTH_SHORT, true).show();
    }
    //===========================================使用ApplicationContext 方法=========================

    //*******************************************常规方法********************************************

    public static void error(@NonNull String message, int duration) {
        error(getContext(), message, duration, true).show();
    }

    public static Toast error(@NonNull String message, int duration, boolean withIcon) {
        return custom(getContext(), message, getDrawable(getContext(), ERROR_DRAWABLE), DEFAULT_TEXT_COLOR, ERROR_COLOR, duration, withIcon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull String message) {
        return normal(context, message, Toast.LENGTH_SHORT, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull String message, Drawable icon) {
        return normal(context, message, Toast.LENGTH_SHORT, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull String message, int duration) {
        return normal(context, message, duration, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull String message, int duration, Drawable icon) {
        return normal(context, message, duration, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull String message, int duration, Drawable icon, boolean withIcon) {
        return custom(context, message, icon, DEFAULT_TEXT_COLOR, duration, withIcon);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull String message) {
        return warning(context, message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull String message, int duration) {
        return warning(context, message, duration, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.drawable.ic_error_outline_white_48dp), DEFAULT_TEXT_COLOR, WARNING_COLOR, duration, withIcon, true);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull String message) {
        return info(context, message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull String message, int duration) {
        return info(context, message, duration, true);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.drawable.ic_info_outline_white_48dp), DEFAULT_TEXT_COLOR, INFO_COLOR, duration, withIcon, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull String message) {
        return success(context, message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull String message, int duration) {
        return success(context, message, duration, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, SUCCESS_DRAWABLE), DEFAULT_TEXT_COLOR, SUCCESS_COLOR, duration, withIcon, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull String message) {
        return error(context, message, Toast.LENGTH_SHORT, true);
    }

    //===========================================常规方法============================================

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull String message, int duration) {
        return error(context, message, duration, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, ERROR_DRAWABLE), DEFAULT_TEXT_COLOR, ERROR_COLOR, duration, withIcon, true);
    }

    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, int duration, boolean withIcon) {
        return custom(context, message, icon, textColor, -1, duration, withIcon, false);
    }

    //*******************************************内需方法********************************************

    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull String message, @DrawableRes int iconRes, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        return custom(context, message, getDrawable(context, iconRes), textColor, tintColor, duration, withIcon, shouldTint);
    }

    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        if (currentToast == null) {
            currentToast = new Toast(context);
        }
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast_layout, null);
        final ImageView toastIcon = (ImageView) toastLayout.findViewById(R.id.toast_icon);
        final TextView toastTextView = (TextView) toastLayout.findViewById(R.id.toast_text);
        Drawable drawableFrame;

        if (shouldTint) {
            drawableFrame = tint9PatchDrawableFrame(context, tintColor);
        } else {
            drawableFrame = getDrawable(context, TOAST_BACKUP);
        }
        setBackground(toastLayout, drawableFrame);

        if (withIcon) {
            if (icon == null) {
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            }
            setBackground(toastIcon, icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        toastTextView.setTextColor(textColor);
        toastTextView.setText(message);
        toastTextView.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);
        currentToast.setGravity(Gravity.CENTER,0,0);
        return currentToast;
    }

    public static final Drawable tint9PatchDrawableFrame(@NonNull Context context, @ColorInt int tintColor) {
        final Drawable toastDrawable = getDrawable(context,TOAST_BACKUP);
        toastDrawable.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
        return toastDrawable;
    }
    //===========================================内需方法============================================


    //******************************************系统 Toast 替代方法***************************************

    public static final void setBackground(@NonNull View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static final Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }*/

    /**
     * 封装了Toast的方法 :需要等待
     *
     * @param context Context
     * @param str     要显示的字符串
     * @param isLong  Toast.LENGTH_LONG / Toast.LENGTH_SHORT
     */
    public static void showToast(Context context, String str, boolean isLong) {
        if (isLong) {
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showToastShort(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showToastShort(int resId) {
        Toast.makeText(getContext(), getContext().getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showToastLong(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showToastLong(int resId) {
        Toast.makeText(getContext(), getContext().getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param msg 显示内容
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.CENTER,0,0);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param resId String资源ID
     */
    public static void showToast(int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), getContext().getString(resId), Toast.LENGTH_LONG);
        } else {
            mToast.setText(getContext().getString(resId));
        }
        mToast.show();
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param context  实体
     * @param resId    String资源ID
     * @param duration 显示时长
     */
    public static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getString(resId), duration);
    }
    //===========================================Toast 替代方法======================================

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param context  实体
     * @param msg      要显示的字符串
     * @param duration 显示时长
     */
    public static void showToast(Context context, String msg, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, duration);
        } else {
            mToast.setText(msg);
        }

        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.show();
    }



    public static Context getContext() {

        return Utils.getApp();
    }
}
