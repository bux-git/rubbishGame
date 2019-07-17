package com.zx.rubbishgame.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description：具体垃圾
 * author：bux on 2019/7/17 10:25
 * email: 471025316@qq.com
 */
public class RbItem implements Parcelable {

    private String name;
    private int img;
    private RbType type;

    private boolean isRight;
    private RbType answer;

    public RbItem(String name, int img, RbType type) {
        this.name = name;
        this.img = img;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public RbType getType() {
        return type;
    }

    public void setType(RbType type) {
        this.type = type;
    }

    public void setAnswer(RbType answer) {
        this.answer = answer;
        isRight = answer.getType() == type.getType();
    }

    public RbType getAnswer() {
        return answer;
    }

    public boolean isRight() {
        return isRight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.img);
        dest.writeParcelable(this.type, flags);
        dest.writeByte(this.isRight ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.answer, flags);
    }

    protected RbItem(Parcel in) {
        this.name = in.readString();
        this.img = in.readInt();
        this.type = in.readParcelable(RbType.class.getClassLoader());
        this.isRight = in.readByte() != 0;
        this.answer = in.readParcelable(RbType.class.getClassLoader());
    }

    public static final Creator<RbItem> CREATOR = new Creator<RbItem>() {
        @Override
        public RbItem createFromParcel(Parcel source) {
            return new RbItem(source);
        }

        @Override
        public RbItem[] newArray(int size) {
            return new RbItem[size];
        }
    };
}
