package com.sobey.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/8/10.
 */
public class SquareHeightImagView extends ImageView {
    public SquareHeightImagView(Context context) {
        super(context);
    }

    public SquareHeightImagView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareHeightImagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
