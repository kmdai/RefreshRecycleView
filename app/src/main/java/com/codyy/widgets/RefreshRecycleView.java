package com.codyy.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;

/**
 * Created by kmdai on 15-12-7.
 */
public class RefreshRecycleView extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener, Handler.Callback {
    /**
     * 刷新完成
     */
    public final static int ON_REFRESH_COMPLETE = 0x001;
    /**
     * 没有数据
     */
    public final static int STATE_NODATA = 0x002;
    /**
     * 正在加载中
     */
    public final static int STATE_LOADING = 0x003;
    /**
     * 没有更多数据
     */
    public final static int STATE_NO_MORE = 0x004;
    /**
     *
     */
    public final static int STATIC_IS_REFRESHING = 0x005;
    /**
     * 上滑加载更多
     */
    public final static int STATE_UP_LOADEMORE = 0x006;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnStateChangeLstener mOnStateChangeLstener;
    private Handler mHandler;
    private Context mContext;
    private RefreshBaseAdapter mRefreshBaseAdapter;

    public RefreshRecycleView(Context context) {
        super(context);
        init(context);
    }

    public RefreshRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mHandler = new Handler(this);
        mLayoutManager = new LinearLayoutManager(mContext);
        this.setOnRefreshListener(this);
        mRecyclerView = new RecyclerView(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
        this.addView(mRecyclerView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrollToBottom()) {
                    Toast.makeText(mContext, "bottom", Toast.LENGTH_SHORT).show();
                    if (mOnStateChangeLstener != null) {
                        mOnStateChangeLstener.onBottom();
                        if (mRefreshBaseAdapter != null && mRefreshBaseAdapter.getmDatas() != null) {
                            mRefreshBaseAdapter.setmState(RefreshRecycleView.STATE_LOADING);
                            mRefreshBaseAdapter.notifyItemChanged(mRefreshBaseAdapter.getmDatas().size());
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private boolean scrollToBottom() {
        if (mLayoutManager != null && mLayoutManager.canScrollVertically()) {
            return !mRecyclerView.canScrollVertically(1);
        } else {
            return !mRecyclerView.canScrollHorizontally(1);
        }
    }

    /**
     * 设置recycleview manager
     * 默认linerlayout
     */
    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        if (mRecyclerView != null) {
            mLayoutManager = manager;
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
    }

    public void setAdapter(RefreshBaseAdapter adapter) {
        mRefreshBaseAdapter = adapter;
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    public void setOnStateChangeLstener(OnStateChangeLstener mOnRefreshListener) {
        this.mOnStateChangeLstener = mOnRefreshListener;
    }

    @Override
    public void onRefresh() {
        if (mOnStateChangeLstener != null) {
            mOnStateChangeLstener.onRefresh();
        } else {
            mHandler.sendEmptyMessageDelayed(ON_REFRESH_COMPLETE, 1500);
        }

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case ON_REFRESH_COMPLETE:
                this.setRefreshing(false);
                Toast.makeText(mContext, "没有处理！", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    public interface OnStateChangeLstener {
        /**
         * 下拉刷新
         */
        void onRefresh();

        void onBottom();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**
     * 返回adapter
     *
     * @return
     */
}
