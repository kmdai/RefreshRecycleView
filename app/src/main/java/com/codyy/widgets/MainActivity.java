package com.codyy.widgets;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RefreshRecycleView.OnStateChangeLstener, Handler.Callback {
    private RefreshRecycleView mRefreshRecycleView;
    private List<RefreshEntity> mDatas;
    Handler handler;
    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(this);
        mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RefreshEntity refreshEntity = new RefreshEntity();
            mDatas.add(refreshEntity);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRefreshRecycleView = (RefreshRecycleView) findViewById(R.id.refreshview);
        mRefreshRecycleView.setOnStateChangeLstener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRefreshRecycleView.setLayoutManager(linearLayoutManager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        adapter = new BaseAdapter(this, mDatas);
        mRefreshRecycleView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (mDatas.size() >= 40) {
            mRefreshRecycleView.setAdapterLastState(RefreshRecycleView.STATE_NO_MORE);
        } else {
            for (int i = 0; i < 20; i++) {
                RefreshEntity refreshEntity = new RefreshEntity();
                mDatas.add(refreshEntity);
            }
            adapter.notifyDataSetChanged();
            mRefreshRecycleView.setRefreshing(false);
        }
        return false;
    }

    class BaseAdapter extends RefreshBaseAdapter {

        public BaseAdapter(Context mContext) {
            super(mContext);
        }

        public BaseAdapter(Context mContext, List<RefreshEntity> mDatas) {
            super(mContext, mDatas);
        }

        @Override
        RecyclerView.ViewHolder getHolderView(ViewGroup parent, int viewType) {
            TextView textView = new TextView(MainActivity.this);
            textView.setText("hahahahahahah");
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150));
            return new RecyclerView.ViewHolder(textView) {
            };
        }

        @Override
        void onBindView(RecyclerView.ViewHolder holder, int position, RefreshEntity entity) {

        }


    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onBottom() {
        handler.sendEmptyMessageDelayed(0x001, 2000);
    }
}
