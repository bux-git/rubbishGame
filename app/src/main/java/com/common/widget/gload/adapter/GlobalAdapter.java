package com.common.widget.gload.adapter;

import android.view.View;

import com.billy.android.loading.Gloading;
import com.common.widget.gload.view.GLoadData;
import com.common.widget.gload.view.GlobalLoadingStatusView;

/**
 * demo to show how to create a {@link Gloading.Adapter}
 * @see GlobalLoadingStatusView
 * @author billy.qi
 * @since 19/3/18 18:37
 */
public class GlobalAdapter implements Gloading.Adapter {

    @Override
    public View getView(Gloading.Holder holder, View convertView, int status) {
        GlobalLoadingStatusView loadingStatusView = null;
        //reuse the old view, if possible
        if (convertView instanceof GlobalLoadingStatusView) {
            loadingStatusView = (GlobalLoadingStatusView) convertView;
        }
        if (loadingStatusView == null) {
            loadingStatusView = new GlobalLoadingStatusView(holder.getContext(), holder.getRetryTask());
        }
        loadingStatusView.setStatus(status);

        //show or not show msg view
      //  boolean hideMsgView = Constants.HIDE_LOADING_STATUS_MSG.equals(data);
       // loadingStatusView.setMsgViewVisibility(!hideMsgView);
        if(holder.getData()!=null) {
            loadingStatusView.setDisPlayData((GLoadData) holder.getData());
        }
        return loadingStatusView;
    }

}
