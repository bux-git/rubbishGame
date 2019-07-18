//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.common.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;

public class ViewPagerLayoutManager extends LinearLayoutManager {
    private static final String TAG = "ViewPagerLayoutManager";
    private static final float MILLISECONDS_PER_INCH = 10f;
    private PagerSnapHelper mPagerSnapHelper;
    private OnViewPagerListener mOnViewPagerListener;
    private RecyclerView mRecyclerView;
    private int mDrift;
    private OnChildAttachStateChangeListener mChildAttachStateChangeListener = new OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnViewPagerListener != null && getChildCount() == 1) {
                mOnViewPagerListener.onInitComplete();
            }

        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
            int releasePos=getPosition(view);
            int curPos=-1;

            if (mDrift >= 0) {
                if (mOnViewPagerListener != null) {

                    mOnViewPagerListener.onPageRelease(true,releasePos ,curPos=findFirstVisibleItemPosition()+1);
                }
            } else if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onPageRelease(false, releasePos,curPos=findFirstVisibleItemPosition());
            }

            Log.d(TAG, "onChildViewDetachedFromWindow: "+mDrift+"   releasePos:"+releasePos+"   curpos:"+curPos);
        }
    };

    public ViewPagerLayoutManager(Context context, int orientation) {
        super(context, orientation, false);
        this.init();
    }

    public ViewPagerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.init();
    }

    private void init() {
        this.mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        this.mPagerSnapHelper.attachToRecyclerView(view);
        this.mRecyclerView = view;
        this.mRecyclerView.addOnChildAttachStateChangeListener(this.mChildAttachStateChangeListener);
    }

    @Override
    public void onLayoutChildren(Recycler recycler, State state) {
        super.onLayoutChildren(recycler, state);
    }

    @Override
    public void onScrollStateChanged(int state) {
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE:
                View viewIdle = this.mPagerSnapHelper.findSnapView(this);
                int positionIdle = 0;
                if (viewIdle != null) {
                    positionIdle = this.getPosition(viewIdle);
                }
                if (this.mOnViewPagerListener != null && this.getChildCount() == 1) {
                    this.mOnViewPagerListener.onPageSelected(positionIdle, positionIdle == this.getItemCount() - 1);
                }
                break;
            case SCROLL_STATE_DRAGGING:
                View viewDrag = this.mPagerSnapHelper.findSnapView(this);
                if (viewDrag != null) {
                    this.getPosition(viewDrag);
                }
                break;
            case SCROLL_STATE_SETTLING:
                View viewSettling = this.mPagerSnapHelper.findSnapView(this);
                if (viewSettling != null) {
                    this.getPosition(viewSettling);
                }
            default:
        }

    }

    @Override
    public int scrollVerticallyBy(int dy, Recycler recycler, State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    @Override
    public int scrollHorizontallyBy(int dx, Recycler recycler, State state) {
        this.mDrift = dx;
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    public void setOnViewPagerListener(OnViewPagerListener listener) {
        this.mOnViewPagerListener = listener;
    }


    /**
     * 控制滑动速度
     *
     * @param recyclerView
     * @param state
     * @param position
     */
    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller smoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    // 返回：滑过1px时经历的时间(ms)。
                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return 100f / displayMetrics.widthPixels;
                    }
                };

        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }
}
