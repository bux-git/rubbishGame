package com.common.base;/*
package com.zx.zxmt3reader.ui.base;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gohojy.www.gohojy.R;
import com.gohojy.www.gohojy.common.widget.MultipleStatusView;
import com.gohojy.www.gohojy.common.widget.RecyclerItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

*/
/**
 * @description：下拉刷新 上拉加载基类
 * @author：bux on 2019/1/11 10:18
 * @email: 471025316@qq.com
 *//*

public abstract class BaseListFragment<T, A extends RecyclerView.Adapter> extends BaseLazyFragment implements OnRefreshLoadmoreListener {
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    public SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.multiView)
    public MultipleStatusView mMultiView;


    public int page = 1;
    public int pageSize = 10;

    public boolean isRefresh = true;

    public A mAdapter;
    public ArrayList<T> mInfoBeans;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_news_collect_layout;
    }

    @Override
    protected void init() {

        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadmore(true);
        mRefreshLayout.setEnableOverScrollBounce(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new RecyclerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mInfoBeans = new ArrayList<>();
        mAdapter = getAdapter();
        if (mAdapter != null) {
            recyclerView.setAdapter(mAdapter);
        }
        mRefreshLayout.setOnRefreshLoadmoreListener(this);

        intData();

    }

    protected abstract void intData();


    public abstract A getAdapter();

    */
/**
     * 获取空布局提示
     *
     * @return
     *//*

    protected abstract int getEmptyTips();

    */
/**
     * 获取空布局
     *
     * @return
     *//*

    protected abstract int getEmptyBgResId();

    @Override
    public void getData() {
        getPreData(true);
    }



    @Override
    public boolean isDataLoaded() {
        return mInfoBeans.size()>0;
    }

    public void getPreData(boolean isShow) {
        page = isRefresh ? 1 : page + 1;
        getData(page, isShow);

    }

    */
/**
     * 解析数据
     *
     * @param beans
     *//*

    public void parseData(List<T> beans) {
        if (beans == null) {
            beans = new ArrayList<>();
        }
        if (isRefresh) {
            if (beans.size() == 0) {
                mMultiView.showCommonEmpty(getEmptyBgResId(), getEmptyTips());
            } else {
                mMultiView.showContent();
            }
            mInfoBeans.clear();
        }

        mInfoBeans.addAll(beans);
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        mRefreshLayout.setLoadmoreFinished(beans.size() < pageSize);

        setUpData();
    }


    */
/**
     * 结束刷新 加载更多动画
     *//*

    public void finishData() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    */
/**
     * 获取数据Data
     *//*

    protected abstract void getData(int page, boolean isShow);


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        isRefresh = false;
        getPreData(false);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        isRefresh = true;
        getPreData(false);
    }

}
*/
