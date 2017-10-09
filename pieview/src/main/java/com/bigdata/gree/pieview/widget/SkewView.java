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
 * Date:2017/10/9
 * Time:10:57
 * Description:
 */

public class SkewView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public SkewView(Context context) {
        this(context, null);
    }

    public SkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rect = new RectF(0, 0, 200, 200);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rect, mPaint);

        canvas.skew(1, 0);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rect, mPaint);
    }
}
