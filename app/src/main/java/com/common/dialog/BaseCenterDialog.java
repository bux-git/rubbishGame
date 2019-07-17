package com.common.dialog;

import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.common.util.DensityUtil;
import com.hwangjr.rxbus.RxBus;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zx.rubbishgame.MyApplication;
import com.zx.rubbishgame.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @description：
 * @author：bux on 2018/8/13 15:31
 * @email: 471025316@qq.com
 */
public abstract class BaseCenterDialog extends BaseFragmentDialog implements LifecycleProvider<FragmentEvent> {

    public static final int DEFAULT_WIDTH = 311;
    public static final int DEFAULT_HEIGHT = ViewGroup.LayoutParams.WRAP_CONTENT;

    public static final double DEFAULT_WIDTH_SCALE = 0.8;
    public static final double DEFAULT_HEIGHT_SCALE = 0.65;

    Unbinder mUnbinder;
    public int scHeight;
    public int scWidth;

    TextView mTvTitle;
    View mIvBack;
    private IntentFilter mKeyWordFilter;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.alert_dialog);
        RxBus.get().register(this);
        scHeight = DensityUtil.getScreenH(getActivity());
        scWidth = DensityUtil.getScreenW(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);

        mTvTitle = view.findViewById(R.id.title);
        mIvBack = view.findViewById(R.id.title_back);
        if (mIvBack != null) {
            mIvBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTitleBack(v);
                }
            });
        }
        return view;
    }

    protected void onTitleBack(View v) {
        dismiss();
    }

    ;

    public abstract int getLayoutResId();

    public void setTitle(String title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }

    /**
     * dialog 高度设置
     *
     * @return
     */
    public int getHeight() {
        return (int) (getHeightScale() * scHeight);
    }

    /**
     * dialog 宽度设置
     *
     * @return
     */
    public int getWidth() {
        return (int) (getWidthScale() * scWidth);
    }


    /**
     * dialog最大高度占用屏幕高度比例
     *
     * @return
     */
    public double getHeightScale() {
        return DEFAULT_HEIGHT_SCALE;
    }

    /**
     * dialog最大宽度比例
     *
     * @return
     */
    public double getWidthScale() {
        return DEFAULT_WIDTH_SCALE;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MyApplication.get().getCountTime(getActivity()).reset();
                return false;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        if (win != null) {
            win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams params = win.getAttributes();

            // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
            params.width = getWidth();
            params.height = getHeight();

            win.setAttributes(params);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        MyApplication.get().getCountTime(getActivity()).reset();
    }

    @Override
    public void onPause() {
        super.onPause();
        MyApplication.get().getCountTime(getActivity()).reset();
    }

    @Override
    public void onDestroy() {
        RxBus.get().register(this);
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }


}
