package com.thinkcoo.www.drawdemo;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by  Leevin
 * on 2016/8/7 ,14:19.
 */
public class MoreTimeView extends FrameLayout{
    public MoreTimeView(Context context) {
        super(context);
        View.inflate(context, R.layout.item_more_time_view, this);
    }
}
