package com.zx.rubbishgame.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.util.ImageLoader;
import com.zx.rubbishgame.R;

import java.util.List;

import butterknife.BindView;

/**
 * description：
 * author：bux on 2019/5/23 10:46
 * email: 471025316@qq.com
 */
public class BannerAdapter extends RecyclerView.Adapter<BaseViewHolder<BannerBean>> {
    private static final String TAG = "BannerAdapter";
    List<BannerBean> mList;
    LayoutInflater mInflater;
    int mWidth;
    int mHeight;

    public BannerAdapter(Context context, int width, int height, List<BannerBean> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
        this.mWidth = width;
        this.mHeight = height;
    }


    @NonNull
    @Override
    public BaseViewHolder<BannerBean> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == BannerBean.VIDEO) {
            return new VideoHolder(mInflater.inflate(R.layout.banner_item_video_layout, viewGroup, false));
        } else {
            return new ImgHolder(mInflater.inflate(R.layout.banner_item_img_layout, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<BannerBean> holder, int pos) {
        holder.setData(mList.get(pos));
    }


    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setList(List<BannerBean> list) {
        mList = list;
        notifyDataSetChanged();

    }

    /**
     * 设置宽高
     */
    private void upWidthHeight(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = mWidth;
        layoutParams.height = mHeight;
        view.setLayoutParams(layoutParams);
        Log.d(TAG, "setUpData: " + layoutParams.height);
    }


    class VideoHolder extends BaseViewHolder<BannerBean> {
        @BindView(R.id.albumImage)
        ImageView mIvCover;
        @BindView(R.id.layoutContainer)
        FrameLayout mVideoContainer;
        @BindView(R.id.layBox)
        RelativeLayout mRltLayBox;

        //封面消失动画
        AlphaAnimation disappearAnimation;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
           /* disappearAnimation = new AlphaAnimation(1, 0);
            disappearAnimation.setDuration(1000);
            disappearAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //mIvCover.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });*/
        }

        @Override
        protected void setUpData(BannerBean bannerBean) {

            mIvCover.setVisibility(View.VISIBLE);
            upWidthHeight(mIvCover);

            //设置封面
            if (bannerBean.getPic() instanceof String
                    && TextUtils.isEmpty(bannerBean.getPic().toString())) {
                Glide.with(itemView.getContext())
                        .setDefaultRequestOptions(
                                new RequestOptions()
                                        .frame(1500 * 1000)
                                        .centerCrop()
                                        .error(R.drawable.ic_default_img))
                        .load(bannerBean.getSource())
                        .into(mIvCover);
            } else {
                ImageLoader.load(bannerBean.getPic(), mIvCover);
                Log.d(TAG, "video: " + bannerBean.getPic());
            }
            mVideoContainer.removeAllViews();
        }


        public void coverGone() {
            if (mIvCover.getVisibility() == View.VISIBLE) {
                mIvCover.startAnimation(disappearAnimation);
            }
        }


    }

    class ImgHolder extends BaseViewHolder<BannerBean> {
        @BindView(R.id.img_container)
        ViewGroup mImgContainer;
        @BindView(R.id.iv_img)
        ImageView mIvImage;

        public ImgHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void setUpData(BannerBean bannerBean) {
            Log.d(TAG, "img: " + bannerBean.getPic());
            //upWidthHeight(mImgContainer);
            ImageLoader.load(bannerBean.getPic(), mIvImage);
        }
    }
}
