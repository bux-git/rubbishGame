package com.zx.rubbishgame.banner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.common.base.BaseFragment;
import com.common.widget.OnViewPagerListener;
import com.common.widget.ViewPagerLayoutManager;
import com.common.widget.avplayer.play.DataInter;
import com.common.widget.avplayer.play.ListPlayer;
import com.common.widget.avplayer.play.ReceiverGroupManager;
import com.kk.taurus.playerbase.entity.DataSource;
import com.kk.taurus.playerbase.event.EventKey;
import com.kk.taurus.playerbase.event.OnPlayerEventListener;
import com.kk.taurus.playerbase.player.IPlayer;
import com.kk.taurus.playerbase.receiver.OnReceiverEventListener;
import com.zx.rubbishgame.R;
import com.zx.rubbishgame.utils.DataCons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * description：Banner
 * author：bux on 2019/5/23 9:13
 * email: 471025316@qq.com
 */
public class BannerFragment extends BaseFragment implements OnPlayerEventListener, OnViewPagerListener {
    private static final String TAG = "BannerFragment";
    //图片跳转到下一个item
    private static final int IMG_TO_NEXT = 1;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.indicator)
    LinearLayout indicator;
    @BindView(R.id.llt_content)
    View mContentView;


    private int mVWidth;
    private int mVHeight;

    List<BannerBean> mList = new ArrayList<>();
    ViewPagerLayoutManager mManager;
    private BannerAdapter mAdapter;

    //当前pos
    private int mCurPos;
    //上一个pos
    private int mPrePos = -1;

    //自动滑动
    boolean isAutoScroll = false;
    //自动滑动 检查任务
    private Disposable checkAutoDisposable;


    public static BannerFragment getInstance(ArrayList<BannerBean> list) {

        BannerFragment bannerFragment = new BannerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("picmovBean", list);
        bannerFragment.setArguments(bundle);
        Log.d(TAG, "getInstance: " + bannerFragment.hashCode());
        return bannerFragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_banner_layout;
    }

    @Override
    protected void init() {
        Log.d(TAG, "init: " + this.hashCode());

        //计算item宽高 16:9
        mVWidth = ScreenUtils.getScreenWidth();//减去边距 等等
        mVHeight = (int) (mVWidth * 9 / 16f);
     /*   mContentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (v.getHeight() > 0) {
                    mContentView.removeOnLayoutChangeListener(this);
                    ViewGroup.LayoutParams params = mContentView.getLayoutParams();
                    params.width = mVWidth;
                    params.height = mVHeight + indicator.getHeight();
                    Log.d(TAG, "width: " + params.width + " height:" + params.height + " oldH:" + mVHeight);
                    mContentView.setLayoutParams(params);
                }
                Log.d(TAG, "onLayoutChange: " + v.getHeight());
            }
        });*/

        initRecycler();
        initPlay();

        setData((List<BannerBean>) getArguments().getSerializable("picmovBean"));
    }


    @Override
    public void onResume() {
        super.onResume();
        if (ListPlayer.get().isInPlaybackState()
                && getCurVideoHolder() != null) {
            ListPlayer.get().resume();
        }
        startAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (ListPlayer.get().getState() != IPlayer.STATE_PLAYBACK_COMPLETE) {
            ListPlayer.get().pause();
        }
        clearAutoScroll();

    }

    @Override
    public void onDestroy() {
        clearAutoScroll();
        ListPlayer.get().destroy();
        mList = null;
        super.onDestroy();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initRecycler() {
        mManager = new ViewPagerLayoutManager(getActivity(), OrientationHelper.HORIZONTAL);
        DefaultItemAnimator defaultItemAnimator = ((DefaultItemAnimator) mRecycler.getItemAnimator());
        if (defaultItemAnimator != null) {
            defaultItemAnimator.setSupportsChangeAnimations(false);
        }
        mRecycler.setLayoutManager(mManager);
        mRecycler.setAdapter(mAdapter = new BannerAdapter(getActivity(), mVWidth, mVHeight, mList));
        mManager.setOnViewPagerListener(this);
        mRecycler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return isAutoScroll;
            }
        });

        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG, "onScrollStateChanged: " + newState);
                //会出现多次调用的情况所以需要额外判断防止多次执行
                // 停止滚动时 开始播放
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    startAutoScroll();
                    if (isAutoScroll) {
                        playItem();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isAutoScroll = false;
                            }
                        }, 1000);
                    }

                } else {
                    clearAutoScroll();
                }
            }

        });

    }


    /**
     * 初始化播放器
     */
    private void initPlay() {
        ListPlayer.get().addOnPlayerEventListener(this);
        //设置控制view
        ListPlayer.get().setReceiverGroup(ReceiverGroupManager.get().getLiteReceiverGroup(getActivity()));
        //显示标题
        ListPlayer.get().updateGroupValue(DataInter.Key.KEY_CONTROLLER_TOP_ENABLE, false);
        ListPlayer.get().updateGroupValue(DataInter.Key.KEY_CONTROLLER_SCREEN_SWITCH_ENABLE, false);
        ListPlayer.get().addOnReceiverEventListener(new OnReceiverEventListener() {
            @Override
            public void onReceiverEvent(int eventCode, Bundle bundle) {
                //Log.d(TAG, "onReceiverEvent: " + eventCode);
                switch (eventCode) {
                    case DataInter.Event.EVENT_CODE_PARENT_DIS_TOUCH:
                        mRecycler.requestDisallowInterceptTouchEvent(bundle.getBoolean(EventKey.BOOL_DATA));
                        break;
                    default:
                }
            }
        });
    }


    private void playPause() {
        int state = ListPlayer.get().getState();
        if (state == IPlayer.STATE_INITIALIZED
                || state == IPlayer.STATE_PREPARED
                || state == IPlayer.STATE_STARTED) {
            ListPlayer.get().pause();
        }
    }


    @Override
    public void onPlayerEvent(int eventCode, Bundle bundle) {
        switch (eventCode) {
            case OnPlayerEventListener.PLAYER_EVENT_ON_VIDEO_RENDER_START:
            case OnPlayerEventListener.PLAYER_EVENT_ON_BUFFERING_END:
               /* //开始播放
                BannerAdapter.VideoHolder holder = getCurVideoHolder();
                if (holder != null) {
                    holder.coverGone();
                }*/

                break;
            case OnPlayerEventListener.PLAYER_EVENT_ON_TIMER_UPDATE:
                //进度播放

                break;
            default:
        }
    }


    /**
     * 获取当前item video holder
     *
     * @return
     */
    public BannerAdapter.VideoHolder getCurVideoHolder() {
        if (mRecycler == null) {
            return null;
        }
        // Log.d(TAG, "getCurVideoHolder: " + mCurPos);
        RecyclerView.ViewHolder videoHolder = mRecycler.findViewHolderForAdapterPosition(mCurPos);
        if (videoHolder instanceof BannerAdapter.VideoHolder) {
            return (BannerAdapter.VideoHolder) videoHolder;
        }
        return null;
    }


    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(List<BannerBean> data) {
        if (data == null) {
            return;
        }
        //第一次无数据时
        //新增 删除数据时
        if (mList.size() == 0) {
            // Log.d(TAG, "setData: " + data.getVersion());

            //创建indicator
            if (data.size() != mList.size()) {
                createIndicator(data.size());
            }
            playPause();
            mList = data;
            mAdapter.setList(mList);
            //选择第一个项目
            mCurPos = 0;
            mPrePos = -1;
            playItem();
        }


    }

    /**
     * 开始当前item 视频播放 或 图片处理
     */
    private void playItem() {
        BannerBean data = mList.get(mCurPos);
        if (data.isVideo()) {
            DataSource dataSource = new DataSource();
            dataSource.setRawId(data.getRawId());
            BannerAdapter.VideoHolder vHolder = getCurVideoHolder();
            if (vHolder != null) {
                ListPlayer.get().attachContainer(vHolder.mVideoContainer, true);
                ListPlayer.get().play(dataSource);

            }
        }

    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isAutoScroll = true;
            //滚动前 停止当前正在播放的视频
            playPause();

            if (mCurPos == (mList.size() - 1)) {
                mCurPos = 0;
            } else {
                mCurPos++;
            }

            Log.d(TAG, " autoScroll: " + mCurPos);
            //最后一项到0
            if (mCurPos == 0) {
                //方法不会调用recyclerview 滑动监听方法
                mManager.scrollToPosition(mCurPos);
                setSelectedIndicators(mCurPos);
                playItem();
            } else {
                mRecycler.smoothScrollToPosition(mCurPos);
                setSelectedIndicators(mCurPos);
            }


        }
    };


    @Override
    public void onInitComplete() {
    }

    @Override
    public void onPageRelease(boolean isNext, int releasePos, int curPos) {
        if (isAutoScroll) {
            return;
        }
        /*
         * 重复释放同一个view时
         *
         * 或按住同一个view左右滑动时不暂停视频
         */
        if (mPrePos == releasePos
                || curPos == mCurPos) {
            return;
        }
        setSelectedIndicators(curPos);
        mPrePos = releasePos;
        playPause();
    }


    @Override
    public void onPageSelected(int position, boolean isBottom) {
        if (isAutoScroll) {
            return;
        }
        if (mCurPos == position) {
            return;
        }
        mCurPos = position;
        setSelectedIndicators(mCurPos);
        if (mPrePos >= 0) {
            mAdapter.notifyItemChanged(mPrePos);
        }
        playItem();
    }


    /**
     * 开启自动滚动
     * 1s检查一次当前是否有自动轮播消息
     */
    private void startAutoScroll() {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        checkAutoDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        //当前有消息时
                        if (mHandler.hasMessages(IMG_TO_NEXT)
                                || mList == null
                                || mList.size() == 0) {
                            return;
                        }


                        //视频 且不是播放状态
                        if (mList.get(mCurPos).isVideo()) {

                            if (!ListPlayer.get().isInPlaybackState()) {
                                mHandler.sendEmptyMessageDelayed(IMG_TO_NEXT, DataCons.BANNER_VIDEO_DELAY);
                            }

                        } else {
                            mHandler.sendEmptyMessageDelayed(IMG_TO_NEXT, DataCons.BANNER_IMG_DELAY);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    /**
     * 移除图片轮播
     */
    private void clearAutoScroll() {
        //Log.d(TAG, "clearAutoScroll: ");
        if (checkAutoDisposable != null) {
            checkAutoDisposable.dispose();
            checkAutoDisposable = null;
        }
        mHandler.removeMessages(IMG_TO_NEXT);
    }


    private List<ImageView> indicatorImages = new ArrayList<>();

    private void createIndicator(int size) {
        indicatorImages.clear();
        indicator.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;

            indicatorImages.add(imageView);
            indicator.addView(imageView, params);
            setSelectIndicator(i, i == 0);
        }
    }

    private void setSelectIndicator(int i, boolean isSel) {
        indicatorImages.get(i).setBackgroundResource(isSel ? R.drawable.banner_select_shape : R.drawable.banner_unselect_shape);
    }

    private void setSelectedIndicators(int pos) {
        for (int i = 0; i < mList.size(); i++) {
            setSelectIndicator(i, i == pos);
        }
    }
}
