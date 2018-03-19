package com.tengo.orchid.View.Custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by johnteng on 2018-03-18.
 */

/**
 * This class functions as a LinearLayout but its height will always match its width
 */
public class SquareGridItemLayout extends LinearLayout {

    public SquareGridItemLayout(Context context) {
        super(context);
    }

    public SquareGridItemLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareGridItemLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SquareGridItemLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
