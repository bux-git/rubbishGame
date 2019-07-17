package com.common.widget.gload.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.rubbishgame.R;

import static com.billy.android.loading.Gloading.STATUS_EMPTY_DATA;
import static com.billy.android.loading.Gloading.STATUS_LOADING;
import static com.billy.android.loading.Gloading.STATUS_LOAD_FAILED;
import static com.billy.android.loading.Gloading.STATUS_LOAD_SUCCESS;

/**
 * simple loading status view for global usage
 * @author billy.qi
 * @since 19/3/19 23:12
 */
@SuppressLint("ViewConstructor")
public class GlobalLoadingStatusView extends LinearLayout implements View.OnClickListener {

    private final TextView mTextView;
    private final Runnable mRetryTask;
    private final ImageView mImageView;

    public GlobalLoadingStatusView(Context context, Runnable retryTask) {
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.view_global_loading_status, this, true);
        mImageView = findViewById(R.id.image);
        mTextView = findViewById(R.id.text);
        this.mRetryTask = retryTask;
        setBackgroundColor(0xFFF0F0F0);
    }

    public void setMsgViewVisibility(boolean visible) {
        mTextView.setVisibility(visible ? VISIBLE : GONE);
    }

    private int emptyMsg=R.string.empty;
    private int emptyImg=R.drawable.icon_empty;

    private int errorMsg=R.string.load_failed;
    private int errorImg=R.drawable.icon_failed;

    private int loadMsg= R.string.loading;
    private int loadAnim=R.drawable.loading;

    public void setStatus(int status) {
        boolean show = true;
        OnClickListener onClickListener = null;
        int image =loadAnim;
        int str = R.string.str_none;
        switch (status) {
            case STATUS_LOAD_SUCCESS: show = false; break;
            case STATUS_LOADING: str =loadMsg; break;
            case STATUS_LOAD_FAILED:
                str =errorMsg ;
               /* Boolean networkConn = isNetworkConnected(getContext());
                if (networkConn != null && !networkConn) {
                    str = R.string.load_failed_no_network;
                }*/
                onClickListener = this;
                image =errorImg ;
                break;
            case STATUS_EMPTY_DATA:
                str = emptyMsg;
                image = emptyImg;
                break;
            default: break;
        }
        mImageView.setImageResource(image);
        setOnClickListener(onClickListener);
        mTextView.setText(str);
        setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void setDisPlayData(GLoadData dp){
        if (dp.getEmptyMsg()>0) {
            emptyMsg=dp.getEmptyMsg();
        }
        if (dp.getEmptyImg()>0) {
            emptyImg = dp.getEmptyImg();
        }
        if (dp.getErrorMsg()>0) {
            errorMsg=dp.getErrorMsg();
        }
        if (dp.getEmptyImg()>0) {
            errorImg = dp.getErrorImg();
        }
        if (dp.getLoadMsg()>0) {
            loadMsg=dp.getLoadMsg();
        }
        if (dp.getLoadAnim()>0) {
            loadAnim = dp.getLoadAnim();
        }

    }

    @Override
    public void onClick(View v) {
        if (mRetryTask != null) {

            mRetryTask.run();
        }
    }
}
