package com.common.widget;

/**
 * Created by 钉某人
 * github: https://github.com/DingMouRen
 * email: naildingmouren@gmail.com
 * 用于ViewPagerLayoutManager的监听
 */

public interface OnViewPagerListener {

    /*初始化完成*/
    void onInitComplete();

    /**
     *
     * @param isNext 往水平左滑动，或垂直上滑动
     * @param releasePos 移出屏幕外 detach的view
     * @param curPos 当前显示在屏幕中的目标View
     */
    void onPageRelease(boolean isNext, int releasePos, int curPos);

    /*选中的监听以及判断是否滑动到底部*/
    void onPageSelected(int position, boolean isBottom);



}
