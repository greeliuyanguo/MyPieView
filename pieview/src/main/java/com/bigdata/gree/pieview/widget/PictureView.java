package com.bigdata.gree.pieview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author:liuyanguo
 * Date:2017/10/9
 * Time:15:09
 * Description:
 */

public class PictureView extends View {

    private int mWidth, mHeight;
    private Paint mPaint;
    private Picture mPicture;

    public PictureView(Context context) {
        this(context, null);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
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

        //create picture object
        mPicture = new Picture();

        //recording content
        Canvas canvas1 = mPicture.beginRecording(500, 500);
        canvas1.translate(250, 100);
        canvas1.drawCircle(0, 0, 100, mPaint);

        mPicture.endRecording();

        mPicture.draw(canvas1);
    }
}
