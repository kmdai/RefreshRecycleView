package com.codyy.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by kmdai on 15-12-11.
 */
public class RefreshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RefreshEntity> mDatas;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size() + 1;
        }
        return 0;
    }
}
