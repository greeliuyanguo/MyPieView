package com.bigdata.gree.pieview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author:liuyanguo
 * Date:2017/10/8
 * Time:15:51
 * Description:Nothing to say.
 */

public class MyRectView extends View {

    private int mWidth, mHeight;
    private Paint mPaint;

    public MyRectView(Context context) {
        this(context, null);
    }

    public MyRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rect = new RectF(-400, -400, 400, 400);
        for (int i = 0; i < 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rect, mPaint);
        }
    }
}
