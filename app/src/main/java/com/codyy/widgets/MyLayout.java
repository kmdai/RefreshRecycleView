package com.codyy.widgets;

import android.content.Context;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by kmdai on 15-12-10.
 */
public class MyLayout extends LinearLayout {
    Scroller mScrollerCompat;

    public MyLayout(Context context) {
        super(context);
        init(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScrollerCompat=new Scroller(context);
//        mScrollerCompat = ScrollerCompat.create(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScrollerCompat.computeScrollOffset()) {
            System.out.println("computeScroll----");
            scrollTo(mScrollerCompat.getCurrX(), mScrollerCompat.getCurrY());
            postInvalidate();
        }
    }

    public void scroll(int a) {
        System.out.println("scroll----"+a);
        mScrollerCompat.startScroll(0, 0, 0, a, 1500);
        invalidate();
    }

}
