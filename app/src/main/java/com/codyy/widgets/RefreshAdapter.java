package com.codyy.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by kmdai on 15-12-11.
 */
public abstract class RefreshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 数据
     */
    private List<RefreshEntity> mDatas;

    private int mState;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return getHolderView(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDatas != null) {
            onBindView(holder, position, mDatas.get(position));
        }
    }

    public List<RefreshEntity> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<RefreshEntity> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size() + 1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas != null) {
            return mDatas.get(position).getmHolderType();
        }
        return super.getItemViewType(position);
    }

    abstract RecyclerView.ViewHolder getHolderView(ViewGroup parent, int viewType);

    abstract void onBindView(RecyclerView.ViewHolder holder, int position, RefreshEntity entity);
}
