package com.thinkcoo.www.drawdemo;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by  Leevin
 * on 2016/8/5 ,20:43.
 */
public class MoreView extends FrameLayout{

    private final View moreView;
    private LinearLayout moreTimeViewContainer;

    public MoreView(Context context) {
        super(context);
        moreView = View.inflate(context, R.layout.item_more_layout_view, this);
        initView();
    }

    private void initView() {
        moreTimeViewContainer = (LinearLayout) moreView.findViewById(R.id.more_time_layout_content_view);
        moreView.findViewById(R.id.add_more_time).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                MoreTimeView moreTimeView = new MoreTimeView(getContext());
                moreTimeViewContainer.addView(moreTimeView);
                if (moreViewListener != null) {
                    moreViewListener.onMoreViewAdded();
                }
            }
        });
    }

    public interface OnMoreViewListener {
        void onMoreViewAdded();
    }
    OnMoreViewListener moreViewListener;

    public void setMoreViewListener(OnMoreViewListener moreViewListener) {
        this.moreViewListener = moreViewListener;
    }
}
