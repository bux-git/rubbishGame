package com.zx.rubbishgame;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.common.base.BaseActivity;
import com.zx.rubbishgame.bean.RbItem;
import com.zx.rubbishgame.bean.RbType;
import com.zx.rubbishgame.utils.DataCons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ResultDialog.OnPlayGameListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.tv_label)
    TextView mTvLabel;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.btn_hs)
    ImageButton mBtnHs;
    @BindView(R.id.btn_yh)
    ImageButton mBtnYh;
    @BindView(R.id.btn_s)
    ImageButton mBtnS;
    @BindView(R.id.btn_g)
    ImageButton mBtnG;

    //当前等级
    private int mCurLv = 0;
    //当前题目序号
    private int mCurPos = 0;
    //存储等级数据
    private SparseArray<List<RbItem>> mData;
    //当前等级分数
    private int mark = 0;
    //存储关卡结果
    private int[] mResult;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        mData = new SparseArray<>();
        mResult = new int[DataCons.getTotalLv()];
        initLvData();
    }


    @OnClick({R.id.btn_hs, R.id.btn_yh, R.id.btn_s, R.id.btn_g})
    public void onViewClicked(View view) {
        MyApplication.get().getCountTime(this).reset();
        switch (view.getId()) {
            case R.id.btn_hs:
                setAnswer(DataCons.HS_TYPE);
                break;
            case R.id.btn_yh:
                setAnswer(DataCons.YH_TYPE);
                break;
            case R.id.btn_s:
                setAnswer(DataCons.S_TYPE);
                break;
            case R.id.btn_g:
                setAnswer(DataCons.G_TYPE);
                break;
            default:
        }
    }

    @Override
    public void onAgain(boolean isLast, boolean isPass) {
        initLvData();
    }

    @Override
    public void onNext() {
        if (mCurLv >= DataCons.getTotalLv() - 1) {
            return;
        }
        mResult = new int[DataCons.getTotalLv()];
        mark = 0;
        mCurLv++;
        mCurPos = 0;
        mData.put(mCurLv, DataCons.getLvData(mCurLv));
        setDataShow();
    }

    /**
     * 初始化等级数据
     */
    private void initLvData() {
        mResult = new int[DataCons.getTotalLv()];
        mark = 0;
        mCurPos = 0;
        mCurLv = 0;
        mData.put(mCurLv, DataCons.getLvData(mCurLv));
        setDataShow();
    }

    private void setDataShow() {
        //设置当前单个数据
        mTvName.setText(getCurItem().getName());
        //设置图片

        //设置序号
        mTvLabel.setText("关卡:" + (mCurLv + 1)
                + "\n"
                + (mCurPos + 1) + "/" + DataCons.LV_COUNT);
    }


    /**
     * 获取当前题目
     *
     * @return
     */
    public RbItem getCurItem() {
        return mData.get(mCurLv).get(mCurPos);
    }


    /**
     * 答题
     *
     * @param hsType
     */
    private void setAnswer(RbType hsType) {

        //设置当前答案
        getCurItem().setAnswer(hsType);
        if (getCurItem().isRight()) {
            mark++;
        }

        //检测是否答完
        if (mCurPos == DataCons.LV_COUNT - 1) {
            mResult[mCurLv] = mark;
            ResultDialog.instance(mCurLv, (ArrayList<RbItem>) mData.get(mCurLv), mResult)
                    .show(getSupportFragmentManager(), "resultDialog");
            return;
        }

        //设置下一个答案
        mCurPos++;
        if (mCurPos < DataCons.LV_COUNT) {
            setDataShow();
        }


    }


}
