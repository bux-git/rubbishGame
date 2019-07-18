package com.common.widget.avplayer.bean;

import com.kk.taurus.playerbase.entity.DataSource;
import com.zx.rubbishgame.MyApplication;

/**
 * description：
 * author：bux on 2019/5/25 11:57
 * email: 471025316@qq.com
 */
public class DataCacheSource extends DataSource {

    public DataCacheSource(String data) {
        super(MyApplication.getProxyUrl(data));
    }

    public DataCacheSource(String tag, String data) {
        super(tag, MyApplication.getProxyUrl(data));
    }

    @Override
    public void setData(String data) {
        super.setData(MyApplication.getProxyUrl(data));
    }

}
