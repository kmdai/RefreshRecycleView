package com.codyy.widgets;

import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kmdai on 15-12-11.
 */
public abstract class RefreshBaseAdapter<T extends RefreshEntity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 数据
     */
    private List<T> mDatas;

    private int mState = RefreshRecycleView.STATE_UP_LOADEMORE;
    private Context mContext;

    public RefreshBaseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public RefreshBaseAdapter(Context mContext, List<T> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    final public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RefreshEntity.REFRESH_TYPE_LASTVIEW) {
            return new LastViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_viewholder_last, parent, false));
        }
        return getHolderView(parent, viewType);
    }

    @Override
    final public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == mDatas.size()) {
            LastViewHolder lastViewHolder = (LastViewHolder) holder;
            lastViewHolder.setState(mState);
        } else {
            if (mDatas != null) {
                onBindView(holder, position, mDatas.get(position));
            }
        }
    }

    public List<T> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size() + 1;
        }
        return 0;
    }

    public T getItem(int position) {
        if (mDatas != null) {
            return mDatas.get(position);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas != null) {
            return position == mDatas.size() ? RefreshEntity.REFRESH_TYPE_LASTVIEW : mDatas.get(position).getmHolderType();
        }
        return super.getItemViewType(position);
    }

    public int getState() {
        return mState;
    }

    public void setState(int mState) {
        this.mState = mState;
    }

    abstract RecyclerView.ViewHolder getHolderView(ViewGroup parent, int viewType);

    abstract void onBindView(RecyclerView.ViewHolder holder, int position, T entity);

    /**
     * 最后显示的viewholder
     */
    class LastViewHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBar;
        TextView mTextView;

        public LastViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.adapter_holder_process);
            mTextView = (TextView) itemView.findViewById(R.id.adapter_holder_textview);
        }

        public void setState(int state) {
            switch (state) {
                case RefreshRecycleView.STATE_NO_MORE:
                    mProgressBar.setVisibility(View.GONE);
                    mTextView.setText(mContext.getString(R.string.refresh_state_no_more));
                    break;
                case RefreshRecycleView.STATE_LOADING:
                    mProgressBar.setVisibility(View.VISIBLE);
                    mTextView.setText(mContext.getString(R.string.refresh_state_loading));
                    break;
                case RefreshRecycleView.STATE_UP_LOADEMORE:
                    mProgressBar.setVisibility(View.GONE);
                    mTextView.setText(mContext.getString(R.string.refresh_state_up_loadmore));
                    break;
            }
        }
    }
}
