package com.bigdata.gree.pieview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author:liuyanguo
 * Date:2017/10/19
 * Time:16:47
 * Description:
 */

public class MyTextView extends View {

    private Paint mPaint;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String str = "ABCDEFG";
        canvas.drawText(str, 200, 500, mPaint);
        str = "qwertyuiop";
        canvas.drawText(str, 2, 5, 200, 600, mPaint);
    }
}
