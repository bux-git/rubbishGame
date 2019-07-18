package com.common.widget.avplayer.cover;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.common.widget.avplayer.play.DataInter;
import com.kk.taurus.playerbase.receiver.BaseCover;
import com.zx.rubbishgame.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CloseCover extends BaseCover {

    @BindView(R.id.iv_close)
    ImageView mCloseIcon;

    private Unbinder unbinder;

    public CloseCover(Context context) {
        super(context);
    }

    @Override
    public void onReceiverBind() {
        super.onReceiverBind();
        unbinder = ButterKnife.bind(this, getView());
    }

    @OnClick({R.id.iv_close})
    public void onViewClick(View view){
        notifyReceiverEvent(DataInter.Event.EVENT_CODE_REQUEST_CLOSE, null);
    }

    @Override
    public void onReceiverUnBind() {
        super.onReceiverUnBind();
        unbinder.unbind();
    }

    @Override
    public View onCreateCoverView(Context context) {
        return View.inflate(context, R.layout.layout_close_cover, null);
    }

    @Override
    public void onPlayerEvent(int eventCode, Bundle bundle) {

    }

    @Override
    public void onErrorEvent(int eventCode, Bundle bundle) {

    }

    @Override
    public void onReceiverEvent(int eventCode, Bundle bundle) {

    }

    @Override
    public int getCoverLevel() {
        return levelMedium(10);
    }
}
