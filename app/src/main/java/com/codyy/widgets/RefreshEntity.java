package com.codyy.widgets;


/**
 * Created by kmdai on 15-12-11.
 */
public class RefreshEntity {
    /**
     * 定义view holder的view类型
     */
    public final static int REFRESH_TYPE_BASE = 0x001;
    /**
     *
     */
    private String mID;
    /**
     * holder类型
     */
    private int mHolderType;

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public int getmHolderType() {
        return mHolderType;
    }

    public void setmHolderType(int mHolderType) {
        this.mHolderType = mHolderType;
    }
}
