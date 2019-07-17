package com.zx.rubbishgame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.common.dialog.FullDialog;
import com.zx.rubbishgame.bean.RbItem;
import com.zx.rubbishgame.utils.DataCons;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * description：汇总dialog
 * author：bux on 2019/7/17 15:03
 * email: 471025316@qq.com
 */
public class ResultDialog extends FullDialog {

    @BindView(R.id.tv_total)
    TextView mTvTotal;
    @BindView(R.id.tv_mark)
    TextView mTvMark;
    @BindView(R.id.btn_again)
    Button mBtnAgain;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    OnPlayGameListener mListener;
    private int lv;
    private boolean isPass;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnPlayGameListener) {
            mListener = (OnPlayGameListener) activity;
        }
    }

    /**
     * @param lv      当前等级
     * @param rbItems 总数据
     * @param marks   关卡得分集合
     * @return
     */
    public static ResultDialog instance(int lv, ArrayList<RbItem> rbItems, int[] marks) {
        Bundle bundle = new Bundle();
        bundle.putInt("lv", lv);
        bundle.putParcelableArrayList("items", rbItems);
        bundle.putIntArray("marks", marks);
        ResultDialog dialog = new ResultDialog();
        dialog.setArguments(bundle);
        // dialog.setCancelable(false);
        return dialog;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.dialog_result_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("闯关结果");
        Bundle bundle = getArguments();
        lv = bundle.getInt("lv");
        ArrayList<RbItem> rbItems = bundle.getParcelableArrayList("items");
        int[] marks = bundle.getIntArray("marks");

        int total = 0;
        for (int i = 0; i <= lv; i++) {
            total += marks[i];
        }
        mTvTotal.setText("累积分数:" + total);
        mTvMark.setText("当前关卡得分:" + marks[lv]);

        isPass=marks[lv] >= DataCons.PASS_SOURCE;
        //及格
        if (isPass) {
            mBtnNext.setVisibility(View.VISIBLE);
            //最后一关
            mBtnNext.setVisibility(DataCons.isLastLv(lv) ? View.GONE : View.VISIBLE);

        } else {
            mBtnNext.setVisibility(View.GONE);
            mBtnAgain.setVisibility(View.VISIBLE);
            if (DataCons.isLastLv(lv)) {

            }
        }

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        BaseQuickAdapter<RbItem, BaseViewHolder> adapter;
        mRecyclerview.setAdapter(adapter = new BaseQuickAdapter<RbItem, BaseViewHolder>(R.layout.item_result_layout, rbItems) {
            @Override
            protected void convert(BaseViewHolder helper, RbItem item) {
                helper.setBackgroundColor(R.id.llt_container
                        , helper.getAdapterPosition() % 2 == 0 ? getResources().getColor(R.color.color_ee) : getResources().getColor(R.color.white));

                helper.setText(R.id.tv_name, item.getName());
                TextView answer = helper.getView(R.id.tv_input);
                TextView result = helper.getView(R.id.tv_result);

                answer.setText(item.getAnswer().getName());

                int color;
                if (item.isRight()) {
                    color = Color.GREEN;
                    result.setText("正确");
                } else {
                    result.setText(item.getType().getName());
                    color = Color.RED;
                }
                answer.setTextColor(color);
                result.setTextColor(color);
            }


        });

        adapter.addHeaderView(getLayoutInflater().inflate(R.layout.item_result_layout, null));

        mBtnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onAgain(DataCons.isLastLv(lv),isPass);
                    dismiss();

                }
            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onNext();
                    dismiss();

                }
            }
        });
    }


    public interface OnPlayGameListener {
        void onAgain(boolean isLast,boolean isPass);

        void onNext();
    }
}
