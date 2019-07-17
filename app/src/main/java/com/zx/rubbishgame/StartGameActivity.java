package com.zx.rubbishgame;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.common.base.BaseFragmentActivity;

/**
 * description：
 * author：bux on 2019/7/17 9:51
 * email: 471025316@qq.com
 */
public class StartGameActivity extends BaseFragmentActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, StartGameActivity.class));
    }



    @Override
    protected int setLayoutId() {
        return R.layout.activity_start_game_layout;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected Fragment getFragment() {
        return new Fragment();
    }

    @Override
    protected int getContainerId() {
        return R.id.container;
    }

    public void startGame(View view) {
        MainActivity.start(this);
    }
}
