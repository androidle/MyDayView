package com.thinkcoo.www.drawdemo;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by  Leevin
 * on 2016/8/5 ,20:32.
 */
public class TestActivity extends AppCompatActivity implements MoreView.OnMoreViewListener {

    private LinearLayout moreLayoutView;
    private MoreView moreView;
    private ViewGroup.LayoutParams layoutParams;
    private LayoutTransition layoutTransition;
    private ScrollView mScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);
        initViews();
    }

    private void initViews() {
        mScrollView = (ScrollView) findViewById(R.id.scroll_view);
        moreLayoutView = (LinearLayout) findViewById(R.id.more_options_content);

//        layoutTransition = new LayoutTransition();
//        setLayoutTransition();
//        moreLayoutView.setLayoutTransition(layoutTransition);
    }

    private void setLayoutTransition() {
        //入场动画:view在这个容器中消失时触发的动画
       /* ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 0f, 360f,0f);
        layoutTransition.setAnimator(LayoutTransition.APPEARING, animIn);*/
        /*ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "alpha", 0f, 1f);
        layoutTransition.setAnimator(LayoutTransition.APPEARING, animIn);
        layoutTransition.setDuration(1000);*/
//        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "translationY", 0 ,100);
//        layoutTransition.setAnimator(LayoutTransition.APPEARING, animIn);
//        layoutTransition.setDuration(1000);
//        //出场动画:view显示时的动画
//        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f);
//        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, animOut);
    }

    public void add(View view) {
        moreView = new MoreView(this);
        moreView.setMoreViewListener(this);
        moreLayoutView.addView(moreView);
        scrollBotton();
    }

    private int getMoreViewHeight(MoreView moreView) {
        int childWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                moreView.getWidth(), View.MeasureSpec.AT_MOST);
        moreView.measure(childWidthMeasureSpec, 0);
        return moreView.getMeasuredHeight();
    }

    public void delete(View view) {
        moreLayoutView.removeView(moreView);
    }

    Handler mHandler = new Handler();
    @Override
    public void onMoreViewAdded() {
        scrollBotton();
    }

    private void scrollBotton() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        },100);
    }
}
