package com.zx.rubbishgame.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description：垃圾类别
 * author：bux on 2019/7/17 10:25
 * email: 471025316@qq.com
 */
public class RbType implements Parcelable {

    private int type;
    private String name;

    public RbType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.name);
    }

    protected RbType(Parcel in) {
        this.type = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<RbType> CREATOR = new Parcelable.Creator<RbType>() {
        @Override
        public RbType createFromParcel(Parcel source) {
            return new RbType(source);
        }

        @Override
        public RbType[] newArray(int size) {
            return new RbType[size];
        }
    };
}
