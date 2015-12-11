package com.codyy.widgets;

import android.support.v4.widget.ScrollerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ScrollerActivity extends AppCompatActivity {

    MyLayout myLayout;
    MyLayout myLayout_root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        myLayout = (MyLayout) getLayoutInflater().inflate(R.layout.scroll_test, null);
        setContentView(R.layout.scroll_test);
        myLayout_root=(MyLayout) findViewById(R.id.mylayout_root);
//        setContentView(myLayout);
        myLayout=(MyLayout)findViewById(R.id.mylayout);
    }

    public void hide(View view) {
        myLayout.scroll(myLayout_root.getChildAt(0).getHeight());
    }

    public void show(View view) {
        myLayout.scroll(-myLayout.getHeight());
    }
}
