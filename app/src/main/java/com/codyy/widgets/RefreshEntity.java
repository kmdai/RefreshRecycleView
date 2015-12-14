package com.codyy.widgets;


/**
 * Created by kmdai on 15-12-11.
 */
public class RefreshEntity {
    /**
     * 最后一个view的类型，集成的不要与此重复，避免错误
     */
    public final static int REFRESH_TYPE_LASTVIEW = 0xa01;
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
