package com.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.util.CommonUtil;
import com.zx.rubbishgame.R;


/**
 * @author Administrator
 */
public class CustomDialog extends Dialog implements View.OnClickListener {
    private String mTitleText;
    private String mContentText;
    private String mCancelText;
    private String mConfirmText;
    private View mDialogView;
    private TextView cancelButt;
    private TextView confirmButt;
    private TextView titleTV;
    private TextView contentTV;
    private View mViewSpit;
    private RelativeLayout title_lout;
    private OnCustomClickListener mCancelClickListener;
    private OnCustomClickListener mConfirmClickListener;
    private Context mContext;
    private int colorID = R.color.color_66;
    private int rightId = R.color.colorAccent;
    private int contentColor = R.color.color_30;
    private int contentSize = 17;
    private int mContentGravity = Gravity.CENTER;
    private boolean mCancelVisible = true;

    public CustomDialog(Context context) {
        super(context, R.style.alert_dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog);
        cancelButt = findViewById(R.id.mCancelButt);
        confirmButt = findViewById(R.id.btn_confirm);
        titleTV = findViewById(R.id.titleTV);
        contentTV = findViewById(R.id.contentTV);
        title_lout = findViewById(R.id.title_lout);
        mViewSpit = findViewById(R.id.view_split);
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);;
        confirmButt.setOnClickListener(this);
        cancelButt.setOnClickListener(this);
        setTitleText(mTitleText);
        setContentText(mContentText);
        setCancelText(mCancelText);
        setConfirmText(mConfirmText);
        setBtnTextColor(colorID);
        setRightBtnTextColor(rightId);
        setCancelVisible(mCancelVisible);
        setContentTextColor(contentColor);
        setContentTextSize(contentSize);
        setContentGravity(mContentGravity);
    }

    public CustomDialog setBtnTextColor(int colorId) {
        this.colorID = colorId;
        int color = ContextCompat.getColor(mContext, colorID);
        if (cancelButt != null) {
            cancelButt.setTextColor(color);
            confirmButt.setTextColor(color);
        }
        return this;
    }

    public CustomDialog setRightBtnTextColor(int colorId) {
        this.rightId = colorId;
        int color = ContextCompat.getColor(mContext, rightId);
        if (confirmButt != null) {
            confirmButt.setTextColor(color);
        }
        return this;
    }

    @Override
    protected void onStart() {
    }

    public CustomDialog setContentText(String text) {
        mContentText = text;
        if (contentTV != null && mContentText != null) {
            contentTV.setText(mContentText);
        }
        return this;
    }

    public CustomDialog setContentTextColor(@ColorRes int resId) {
        contentColor = resId;
        if (contentTV != null) {
            CommonUtil.setTextColor(contentTV, resId);
        }
        return this;
    }

    public CustomDialog setContentTextSize(int sizeSP) {
        contentSize = sizeSP;
        if (contentTV != null) {
            contentTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeSP);
        }
        return this;
    }

    public CustomDialog setContentGravity(int gravity) {
        mContentGravity = gravity;
        if (contentTV != null) {
            contentTV.setGravity(mContentGravity);
        }
        return this;
    }

    public CustomDialog setTitleText(String text) {
        mTitleText = text;
        if (titleTV != null && !TextUtils.isEmpty(mTitleText)) {
            titleTV.setText(mTitleText);
        } else {
            if (title_lout != null) {
                title_lout.setVisibility(View.GONE);
                contentTV.setGravity(Gravity.CENTER);
            }
        }
        return this;
    }

    public CustomDialog setCancelText(String text) {
        mCancelText = text;
        if (cancelButt != null && mCancelText != null) {
            cancelButt.setText(mCancelText);
        }
        return this;
    }

    public CustomDialog setConfirmText(String text) {
        mConfirmText = text;
        if (confirmButt != null && mConfirmText != null) {
            confirmButt.setText(mConfirmText);
        }
        return this;
    }


    public CustomDialog setCancelClickListener(OnCustomClickListener listener) {
        mCancelClickListener = listener;
        return this;
    }

    public CustomDialog setConfirmClickListener(OnCustomClickListener listener) {
        mConfirmClickListener = listener;
        return this;
    }

    public CustomDialog setCancelVisible(boolean visible) {
        mCancelVisible = visible;
        if (cancelButt != null) {
            cancelButt.setVisibility(mCancelVisible ? View.VISIBLE : View.GONE);
            mViewSpit.setVisibility(mCancelVisible ? View.VISIBLE : View.GONE);
        }
        return this;
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mCancelButt) {
            if (mCancelClickListener != null) {
                mCancelClickListener.onClick(CustomDialog.this);
            }
            dismiss();

        } else if (i == R.id.btn_confirm) {
            if (mConfirmClickListener != null) {
                mConfirmClickListener.onClick(CustomDialog.this);
            }

        } else {
        }
    }

    public interface OnCustomClickListener {
        /**
         * 按钮点击
         *
         * @param customDialog dialog
         */
        void onClick(CustomDialog customDialog);
    }
}
